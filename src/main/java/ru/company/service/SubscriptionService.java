package ru.company.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.company.domain.dto.SubscriptionsRequest;
import ru.company.domain.model.*;
import ru.company.repository.SubscriptionRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SubscriptionService {
    private final SubscriptionRepository repository;


    public Subscription createSubscription(SubscriptionsRequest request) {
        Subscription subscription = new Subscription();
        subscription.setStreamingService(request.getService());
        return repository.save(subscription);
    }

    public List<Subscription> getAllSubscription() {
        return repository.findAll();
    }

    public Subscription getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    public Subscription getByService(String streamingService) {
        return repository.findByStreamingService(streamingService)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
