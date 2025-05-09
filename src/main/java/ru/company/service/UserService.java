package ru.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.company.domain.dto.UpdateUserRequest;
import ru.company.domain.model.Role;
import ru.company.domain.model.Subscription;
import ru.company.domain.model.User;
import ru.company.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;

    public User save(User user) {
        return repository.save(user);
    }

    public User create(User user) {
        if (repository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("Пользователь с таким именем уже существует");
        }
        if (repository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Пользователь с таким email уже существует");
        }
        return save(user);
    }

    public User getByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public User getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public UserDetailsService userDetailsService() {
        return this::getByUsername;
    }

    public User getCurrentUser() {
        var username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getByUsername(username);
    }

    public void getAdmin() {
        var user = getCurrentUser();
        user.setRole(Role.ROLE_ADMIN);
        save(user);
    }

    public User updateUser(Long id, UpdateUserRequest request) {
        User user = getById(id);
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        return repository.save(user);
    }

    public List<Subscription> getSubscription(Long id) {
        return getById(id).getSubscriptions();
    }

    public User addSubscription(Long id, Subscription subscription) {
        User user = getById(id);
        if (!user.getSubscriptions().contains(subscription)) {
            user.getSubscriptions().add(subscription);
        }
        return repository.save(user);
    }

    public User deleteSubscription(Long id, Subscription subscription) {
        User user = getById(id);
        if (user.getSubscriptions().contains(subscription)) {
            List<Subscription> sub;
            sub = user.getSubscriptions();
            sub.remove(subscription);
            user.setSubscriptions(sub);
        }
        return repository.save(user);
    }

    public void delete(Long id) {
        repository.delete(getById(id));
    }

    public Map<String, Integer> getSubscriptionTop() {
        List<User> users;
        users = repository.findAll();
        List<Subscription> sub = new ArrayList<>();
        for (User user : users) {
            sub.addAll(user.getSubscriptions());
        }
        Map<String, Integer> map;
        map = sub.stream().collect(Collectors.toMap(Subscription::getStreamingService, i -> 1, Integer::sum));
        map = map.entrySet().stream().sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(3).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        return map;
    }
}
