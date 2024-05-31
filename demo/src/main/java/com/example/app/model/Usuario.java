@Entity
public class Usuario {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String nombreUsuario;

  private String nombreCompleto;

  private String correoElectronico;

  private String contrasena;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "usuario_favoritos",
      joinColumns = @JoinColumn(name = "usuario_id"),
      inverseJoinColumns = @JoinColumn(name = "manga_id"))
  private Set<Manga> favoritos = new HashSet<>();

  // Getters y setters
}

