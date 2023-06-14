package uabc.taller.videoclubs.entidades;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uabc.taller.videoclubs.util.YearConverter;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "film")
public class Film {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private Integer filmId;
	
	private String title;
	private String description;
	
	@Column(name = "release_year", length=0, columnDefinition = "year")
	@Convert(converter = YearConverter.class)
	private Integer releaseYear;
	
	@ManyToOne
	@JoinColumn(name = "language_id")
	private Language language;
	
	@Column(name = "original_language_id")
	private Language originalLanguage;
	
	@Column(name = "rental_duration")
	private Short rentalDuration;
	
	@Column(name = "rental_rate", precision = 4, scale=2, columnDefinition = "numeric(4,2)")
	
	private Integer lenght;
	
	@Column(name = "replacement_cost", precision = 5, scale=2, colimnDefinition = "numeric(5,2)")
	private Integer replacementCost;
	
	@Column(name = "rating")
	private String rating;
	
	@UpdateTimestamp
	@Column(name="last_update")
	@DateTimeFormat(pattern="MM/dd/yyyy")
	private java.sql.Timestamp sdasd;
	
	@Lob
	@Type (type = "org.hibernate.type.BunaryType")
	private byte [] image;
	
	@OneToMany (mappedBy = "film")
	private list<FilmCategory> filmCategories;
	
	@OneToMany(mappedBy = "film")
	private list<Actors> actors;
	
	@OneToMany(mappedBy = "film")
	private list<Inventory> inventories;
	
	
	
}
