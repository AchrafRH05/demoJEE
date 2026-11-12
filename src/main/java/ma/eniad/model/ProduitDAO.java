package ma.eniad.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ProduitDAO {

    private final ConcurrentHashMap<Long, Produit> produits =
            new ConcurrentHashMap<>();

    private final AtomicLong sequence = new AtomicLong(0);

    public ProduitDAO() {
        save(new Produit(null, "Laptop", "Dell XPS 13",
                new java.math.BigDecimal("9500.00"), 5));

        save(new Produit(null, "Souris", "Logitech MX Master",
                new java.math.BigDecimal("650.00"), 20));
    }

    // Retrieve all products
    public List<Produit> findAll() {
        List<Produit> liste = new ArrayList<>(produits.values());

        liste.sort(Comparator.comparing(Produit::getId));

        return liste;
    }

    // Retrieve one product by ID
    public Produit findById(Long id) {
        return produits.get(id);
    }

    // Add or update a product
    public void save(Produit produit) {
        if (produit.getId() == null) {
            produit.setId(sequence.incrementAndGet());
        }

        produits.put(produit.getId(), produit);
    }

    // Delete a product
    public void delete(Long id) {
        produits.remove(id);
    }
}
