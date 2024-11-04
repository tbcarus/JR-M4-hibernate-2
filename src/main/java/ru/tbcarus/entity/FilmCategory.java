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
@Table(name = "film_category")
public class FilmCategory {

    @Id
    @ManyToOne
    @JoinColumn(name = "film_id")
    @ToString.Exclude
    private Film film;

    @Id
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;
}
