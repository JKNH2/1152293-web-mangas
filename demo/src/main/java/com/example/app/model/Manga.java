@Entity
public class Manga {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombre;

  @Column(name = "fecha_lanzamiento")
  @Temporal(TemporalType.DATE)
  private Date fechaLanzamiento;

  private Integer numeroTemporadas;

  private String paisOrigen;

  private boolean juego;

  private boolean anime;

  private boolean pelicula;
  
  private String tipo;

  
}
