package ru.company.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.company.domain.model.Subscription;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Optional<Subscription> findByStreamingService(String StreamingService);

    Optional<Subscription> findById(Long id);

    @Override
    List<Subscription> findAll();
}
