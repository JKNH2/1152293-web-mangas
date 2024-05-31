@RestController
@RequestMapping("/mangas")
public class MangaController {

  private final MangaRepository mangaRepository;

  public MangaController(MangaRepository mangaRepository) {
    this.mangaRepository = mangaRepository;
  }

  @GetMapping
  public List<Manga> getAllMangas() {
    return mangaRepository.findAll();
  }

  @GetMapping("/{id}")
  public Manga getMangaById(@PathVariable Long id) {
    return mangaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Manga no encontrado"));
  }

  @PostMapping
  public Manga createManga(@RequestBody Manga manga) {
    return mangaRepository.save(manga);
  }

  @PutMapping("/{id}")
  public Manga updateManga(@PathVariable Long id, @RequestBody Manga manga) {
    Manga existingManga = mangaRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Manga no encontrado"));
    existingManga.setNombre(manga.getNombre());
    existingManga.setFechaLanzamiento(manga.getFechaLanzamiento());
    existingManga.setNumeroTemporadas(manga.getNumeroTemporadas());
    existingManga.setPaisOrigen(manga.getPaisOrigen());
    existingManga.setJuego(manga.isJuego());
    existingManga.setAnime(manga.isAnime());
    existingManga.setPelicula(manga.isPelicula());
    existingManga.setTipo(manga.getTipo());
    return mangaRepository.save(existingManga);
  }

  @DeleteMapping("/{id}")
  public void deleteManga(@PathVariable Long id) {
    mangaRepository.deleteById(id);
  }
}
