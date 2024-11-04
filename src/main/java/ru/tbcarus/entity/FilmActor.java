package ru.tbcarus.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "film_actor")
public class FilmActor {

    @Id
    @ManyToOne
    @JoinColumn(name = "actor_id")
    private Actor actor;

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id")
    @ToString.Exclude
    private Film film;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
