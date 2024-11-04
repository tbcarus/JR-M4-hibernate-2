package ru.tbcarus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "film_text")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmText {

    @Id
//    @Column(name = "film_id")
    private Short filmId;

    @OneToOne
    @PrimaryKeyJoinColumn(name = "film_id")
    @MapsId
    private Film film;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;
}
