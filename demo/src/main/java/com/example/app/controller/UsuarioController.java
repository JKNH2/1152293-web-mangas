@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UsuarioRepository usuarioRepository;
  private final MangaRepository mangaRepository;

  public UsuarioController(UsuarioRepository usuarioRepository, MangaRepository mangaRepository) {
    this.usuarioRepository = usuarioRepository;
    this.mangaRepository = mangaRepository;
  }

  @GetMapping
  public List<Usuario> getAllUsuarios() {
    return usuarioRepository.findAll();
  }

  @GetMapping("/{id}")
  public Usuario getUsuarioById(@PathVariable Long id) {
    return usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
  }

  @PostMapping
  public Usuario createUsuario(@RequestBody Usuario usuario) {
    return usuarioRepository.save(usuario);
  }

  @PutMapping("/{id}")
  public Usuario updateUsuario(@PathVariable Long id, @RequestBody Usuario usuario) {
    Usuario existingUsuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    existingUsuario.setNombreUsuario(usuario.getNombreUsuario());
    existingUsuario.setNombreCompleto(usuario.getNombreCompleto());
    existingUsuario.setCorreoElectronico(usuario.getCorreoElectronico());
    existingUsuario.setContrasena(usuario.getContrasena());
    return usuarioRepository.save(existingUsuario);
  }

  @DeleteMapping("/{id}")
  public void deleteUsuario(@PathVariable Long id) {
    usuarioRepository.deleteById(id);
  }

  @GetMapping("/{id}/favoritos")
  public List<Manga> getFavoritosUsuario(@PathVariable Long id) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    return usuario.getFavoritos();
  }

  @PostMapping("/{id}/favoritos/{mangaId}")
  public Usuario addFavoritoUsuario(@PathVariable Long id, @PathVariable Long mangaId) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    Manga manga = mangaRepository.findById(mangaId)
        .orElseThrow(() -> new ResourceNotFoundException("Manga no encontrado"));
    usuario.getFavoritos().add(manga);
    return usuarioRepository.save(usuario);
  }

  @DeleteMapping("/{id}/favoritos/{mangaId}")
  public Usuario removeFavoritoUsuario(@PathVariable Long id, @PathVariable Long mangaId) {
    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));
    Manga manga = mangaRepository.findById(mangaId)
        .orElseThrow(() -> new ResourceNotFoundException("Manga no encontrado"));
    usuario.getFavoritos().remove(manga);
    return usuarioRepository.save
    }
}
