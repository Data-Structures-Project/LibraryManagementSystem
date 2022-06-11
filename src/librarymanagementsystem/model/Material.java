package librarymanagementsystem.model;

import java.util.Date;
import java.util.Objects;
import java.util.Stack;

public class Material implements Comparable<Material> {
    private Long id;
    private MaterialType type;
    private String name;
    private Category category;
    private Date publicationDate;
    private Author author;
    private Publisher publisher;
    private int pageCount;
    private Situation situation;
    private Location location;
    private Stack<Integer> rates;
    private Double rateAve;
    private String info;

    public Material(Long id, MaterialType type, String name, Category category, Date publicationDate, Author author,
            Publisher publisher, int pageCount, Situation situation, Location location, Double rateAve, String info) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.category = category;
        this.publicationDate = publicationDate;
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.situation = situation;
        this.location = location;
        this.rateAve = rateAve;
        this.info = info;
        rates = new Stack<>();
    }

    public Material(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MaterialType getType() {
        return type;
    }

    public void setType(MaterialType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Stack<Integer> getRates() {
        return rates;
    }

    public void setRates(Stack<Integer> rates) {
        this.rates = rates;
    }

    public boolean addRate(Integer rate) {
        return rates.add(rate);
    }

    public boolean removeRate(Integer rate) {
        return rates.remove(rate);
    }

    public Double getRateAve() {
        for (Integer integer : rates) {
            this.rateAve += integer;
        }
        return rateAve = this.rateAve/ rates.size();
    }

    public String getInfo() {
        return this.info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * Copies the newMaterial's data to the this material
     * @param newMaterial The material object that has new data
     */
    public void setMaterial(Material newMaterial)
    {
        this.author = newMaterial.author;
        this.category = newMaterial.category;
        this.id = newMaterial.id;
        this.pageCount = newMaterial.pageCount;
        this.info = newMaterial.info;;
        this.name = newMaterial.name;
        this.type = newMaterial.type;
        this.location = newMaterial.location;
        this.publicationDate = newMaterial.publicationDate;
        this.rates = newMaterial.rates;
        this.situation = newMaterial.situation;
    }

    /**
     * TODO BUNU İSME GÖRE COMPARE ET
     * 
     * @param material the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Material material) {
        try {
            if (material == null)
                throw new NullPointerException();

            else if (id < material.id)
                return -1;

            else if (id > material.id)
                return 1;

            else
                return 0;
        } catch (NullPointerException e) {
            System.out.println("Material.compareTo: material is null");
        }

        return -1;
    }

    public int compareTo(Integer ID) {
        if (id < ID)
            return -1;

        else if (id > ID)
            return 1;

        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Material))
            return false;
        Material material = (Material) o;
        return getPageCount() == material.getPageCount() && Objects.equals(getId(), material.getId())
                && getType() == material.getType() && Objects.equals(getName(), material.getName())
                && getCategory() == material.getCategory()
                && Objects.equals(getPublicationDate(), material.getPublicationDate())
                && Objects.equals(getAuthor(), material.getAuthor())
                && Objects.equals(getPublisher(), material.getPublisher()) && getSituation() == material.getSituation()
                && getLocation() == material.getLocation() && Objects.equals(getRates(), material.getRates());
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", publicationDate=" + publicationDate +
                ", author=" + author +
                ", publisher=" + publisher +
                ", pageCount=" + pageCount +
                ", situation=" + situation +
                ", location=" + location +
                ", rates=" + rates +
                '}';
    }
}
