package uabc.taller.videoclubs.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

@Entity
@Table(name = "film_actor")
public class FilmActor {
	
	@EmbeddedId
	private FilmActorId id;
	
	@ManyToOne
	@MapsId("filmId")
	@Join()
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "actor_id")
	private Integer actorId;
	
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column(name = "film_id")
	private Integer filmId;

}
