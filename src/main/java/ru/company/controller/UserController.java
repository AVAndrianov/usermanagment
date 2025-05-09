package ru.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.company.domain.dto.*;
import ru.company.domain.model.Subscription;
import ru.company.domain.model.User;
import ru.company.service.AuthenticationService;
import ru.company.service.SubscriptionService;
import ru.company.service.UserService;

import java.util.List;
import java.util.Map;

@Tag(name = "User", description = "The User API")
@RestController
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final SubscriptionService subscriptionService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @Operation(summary = "Добавить пользователя")
    @PostMapping("/users")
    public JwtAuthenticationResponse addUser(@RequestBody @Valid SignUpRequest request) {
        log.info("Test info");
        logger.info(String.valueOf(request));
        return authenticationService.signUp(request);
    }

    @Operation(summary = "Получить токен пользователя")
    @PostMapping("/sign-in")
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
        return authenticationService.signIn(request);
    }

    @Operation(summary = "Получить информациб о пользователе")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    @GetMapping
    public User getUser(@PathVariable Long id) {
        return userService.getById(id);
    }

    @Operation(summary = "Список всех пользователей")
    @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
    @GetMapping
    public List<User> getAllUsers() {
        return authenticationService.findAll();
    }

    @Operation(summary = "Удалить пользователя")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @Validated
    @Operation(summary = "Обновить данные пользователя")
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public User updateUser(@RequestBody @Valid UpdateUserRequest request, @PathVariable Long id) {
        return userService.updateUser(id, request);
    }

    @Operation(summary = "Добавить подписку пользователю")
    @PostMapping("/users/{id}/subscriptions")
    public User addSubscriptions(@RequestBody @Valid SubscriptionsRequest request, @PathVariable Long id) {
        return userService.addSubscription(id, subscriptionService.getByService(request.getService()));
    }

    @Operation(summary = "Получить подписки пользователя")
    @RequestMapping(value = "/users/{id}/subscriptions", method = RequestMethod.GET)
    public List<Subscription> getSubscriptions(@PathVariable Long id) {
        return userService.getSubscription(id);
    }

    @Operation(summary = "Отписать пользователя от сервиса")
    @RequestMapping(value = "/users/{id}/subscriptions/{sub_id}", method = RequestMethod.DELETE)
    public User deleteSubscriptions(@PathVariable Long id, @PathVariable Long sub_id) {
        return userService.deleteSubscription(id, subscriptionService.getById(sub_id));
    }

    @Operation(summary = "Топ 3 популярных сервиса")
    @RequestMapping(value = "/subscriptions/top", method = RequestMethod.GET)
    public Map<String, Integer> getSubscriptionTop() {
        return userService.getSubscriptionTop();
    }
}

