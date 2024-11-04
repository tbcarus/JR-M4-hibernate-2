package ru.tbcarus.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import ru.tbcarus.entity.converters.RatingConverter;
import ru.tbcarus.entity.enums.Rating;
import ru.tbcarus.entity.enums.SpecialFeatures;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private Short id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "release_year", columnDefinition = "year")
    private Year releaseYear;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration")
    private Byte rentalDuration;

    @Column(name = "rental_rate")
    private BigDecimal rentalRate;

    @Column(name = "length")
    private Short length;

    @Column(name = "replacement_cost")
    private BigDecimal replacementCost;

    @Column(name = "rating", columnDefinition = "enum('G', 'PG', 'PG-13', 'R', 'NC-17')")
    @Convert(converter = RatingConverter.class)
    private Rating rating;

    @Column(name = "special_features", columnDefinition = "set('Trailers', 'Commentaries', 'Deleted Scenes', 'Behind the Scenes')")
    private String specialFeatures;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<FilmActor> actors;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL)
    private List<FilmCategory> categories;

    @UpdateTimestamp
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public Set<SpecialFeatures> getSpecialFeatures() {
        Set<SpecialFeatures> sf = new HashSet<>();
        if (specialFeatures == null || specialFeatures.isEmpty()) {
            return sf;
        }
        for (String s : specialFeatures.split(",")) {
            sf.add(SpecialFeatures.getByValue(s));
        }
        sf.remove(null);
        return sf;
    }

    public void setSpecialFeatures(Set<SpecialFeatures> set) {
        if (set == null || set.isEmpty()) {
            this.specialFeatures = null;
        } else {
            this.specialFeatures = set.stream().map(SpecialFeatures::getValue).collect(Collectors.joining(","));
        }

    }

    public void addSpecialFeature(SpecialFeatures sf) {
        this.specialFeatures = "," + sf.toString();
    }
}
