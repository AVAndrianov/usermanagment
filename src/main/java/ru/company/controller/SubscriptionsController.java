package ru.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.company.domain.dto.*;
import ru.company.domain.model.Subscription;
import ru.company.service.SubscriptionService;

import java.util.List;

@Tag(name = "Subscriptions", description = "The Subscriptions API")
@RestController
@RequiredArgsConstructor
public class SubscriptionsController {
    private final SubscriptionService subscriptionService;

    @Operation(summary = "Добавить сервис")
    @PostMapping("/users/createSubscriptions")
    public Subscription createSubscriptions(@RequestBody @Valid SubscriptionsRequest request) {
        return subscriptionService.createSubscription(request);
    }

    @Operation(summary = "Список всех сервисов")
    @RequestMapping(value = "/users/getAllSubscriptions", method = RequestMethod.GET)
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscription();
    }

}

