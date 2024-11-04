package ru.tbcarus;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.tbcarus.config.SessionFactoryConfig;
import ru.tbcarus.entity.*;
import ru.tbcarus.entity.enums.Rating;
import ru.tbcarus.entity.enums.SpecialFeatures;
import ru.tbcarus.repository.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class Main {

    SessionFactory sessionFactory = SessionFactoryConfig.getSessionFactory();
    ActorRepository actorRepository;
    AddressRepository addressRepository;
    CategoryRepository categoryRepository;
    CityRepository cityRepository;
    CountryRepository countryRepository;
    CustomerRepository customerRepository;
    FilmActorRepository FilmActorRepository;
    FilmCategoryRepository filmCategoryRepository;
    FilmRepository filmRepository;
    FilmTextRepository filmTextRepository;
    InventoryRepository inventoryRepository;
    LanguageRepository languageRepository;
    PaymentRepository paymentRepository;
    RentalRepository rentalRepository;
    StaffRepository staffRepository;
    StoreRepository storeRepository;

    public Main() {
        actorRepository = new ActorRepository(sessionFactory);
        addressRepository = new AddressRepository(sessionFactory);
        categoryRepository = new CategoryRepository(sessionFactory);
        cityRepository = new CityRepository(sessionFactory);
        countryRepository = new CountryRepository(sessionFactory);
        customerRepository = new CustomerRepository(sessionFactory);
        FilmActorRepository = new FilmActorRepository(sessionFactory);
        filmCategoryRepository = new FilmCategoryRepository(sessionFactory);
        filmRepository = new FilmRepository(sessionFactory);
        filmTextRepository = new FilmTextRepository(sessionFactory);
        inventoryRepository = new InventoryRepository(sessionFactory);
        languageRepository = new LanguageRepository(sessionFactory);
        paymentRepository = new PaymentRepository(sessionFactory);
        rentalRepository = new RentalRepository(sessionFactory);
        staffRepository = new StaffRepository(sessionFactory);
        storeRepository = new StoreRepository(sessionFactory);
    }

    public static void main(String[] args) {
        Main main = new Main();

        System.out.println("Hello world!");

//        main.sessionFactory.inTransaction(session -> {
//            Film film = session.find(Film.class, 19);
//            System.out.println(film);
//            int x = 5;
//        });

//        main.createCustomerWithAddress();
//        main.returnFilm();
//        main.returnFilm();
//        main.newFilmRent();
        main.addNewFilm();
    }

    public void createCustomerWithAddress() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Country cuba = Country.builder()
                    .country("Cuba")
                    .build();
            City matanzas = City.builder()
                    .city("Matanzas")
                    .country(cuba)
                    .build();
            Address address = Address.builder()
                    .address("Cale 121")
                    .district("MZS")
                    .city(matanzas)
                    .postalCode("123456")
                    .phone("+5352123456")
                    .build();
            Store store = storeRepository.getById(1);
            Customer customer = Customer.builder()
                    .store(store)
                    .firstName("Ivan")
                    .lastName("Ivanov")
                    .email("ivanov@gmail.com")
                    .address(address)
                    .active(true)
                    .build();
            customerRepository.save(customer);

            transaction.commit();
        }
    }

    public void returnFilm() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Rental rental = rentalRepository.findAnyFilmToReturn();
            rental.setReturnDate(LocalDateTime.now());

            transaction.commit();
        }
    }

    public void newFilmRent() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Customer customer = customerRepository.getById(1);
            Inventory inventory = getRandomAvailableInventory();
            Film film = inventory.getFilm();
            Store store = inventory.getStore();
            Staff staff = store.getManagerStaff();

            Rental newRental = Rental.builder()
                    .rentalDate(LocalDateTime.now())
                    .inventory(inventory)
                    .customer(customer)
                    .returnDate(null)
                    .staff(staff)
                    .build();
            rentalRepository.save(newRental);

            Payment newPayment = Payment.builder()
                    .customer(customer)
                    .staff(staff)
                    .rental(newRental)
                    .amount(film.getRentalRate())
                    .paymentDate(LocalDateTime.now())
                    .build();
            System.out.println(newPayment.getRental().getId());
            paymentRepository.save(newPayment);

            transaction.commit();
        }
    }

    public Inventory getRandomAvailableInventory() {
        List<Inventory> inventoryList = inventoryRepository.getAll();
        Random random = new Random();
        boolean founded = false;
        Inventory inventory = null;

        while (!founded) {
            inventory = inventoryList.get(random.nextInt(inventoryList.size()));
            List<Rental> rentalList = rentalRepository.getByInventoryId(inventory.getId());
            if (rentalList == null || rentalList.isEmpty()) {
                founded = true;
            } else {
                founded = rentalList.stream().allMatch(rental -> rental.getReturnDate() != null);
            }
        }
        return inventory;
    }

    public void addNewFilm() {
        try (Session session = sessionFactory.getCurrentSession()) {
            Transaction transaction = session.beginTransaction();

            Language language = languageRepository.getAll().stream().findAny().get();

            Film film = Film.builder()
                    .title("New film NG-17")
                    .description("New film description")
                    .releaseYear(Year.of(2024))
                    .language(language)
                    .rentalDuration((byte) 3)
                    .rentalRate(BigDecimal.valueOf(5.54))
                    .length((short) 156)
                    .replacementCost(BigDecimal.valueOf(20.01))
                    .rating(Rating.NC17)
                    .build();
            film.setSpecialFeatures(Set.of(SpecialFeatures.COMMENTARIES, SpecialFeatures.BEHIND_THE_SCENES));
            List<Category> categories = categoryRepository.getPage(0, 5);
            List<Actor> actors = actorRepository.getPage(0, 10);
            film.setCategories(toFilmCategory(categories, film));
            film.setActors(toFilmActor(actors, film));
            filmRepository.save(film);

            FilmText filmText = FilmText.builder()
                    .film(film)
                    .title("New film film text")
                    .description("New film description film text")
                    .build();
            filmTextRepository.save(filmText);

            List<Store> stores = storeRepository.getAll();
            for (Store store : stores) {
                for (int i = 0; i < film.getRentalDuration(); i++) {
                    Inventory inventory = Inventory.builder()
                            .film(film)
                            .store(store)
                            .build();
                    inventoryRepository.save(inventory);
                }
            }
            transaction.commit();
        }
    }

    private List<FilmCategory> toFilmCategory(List<Category> categories, Film film) {
        List<FilmCategory> list = new ArrayList<>();
        for (Category category : categories) {
            list.add(FilmCategory.builder().category(category).film(film).build());
        }
        return list;
    }

    private List<FilmActor> toFilmActor(List<Actor> actors, Film film) {
        List<FilmActor> list = new ArrayList<>();
        for (Actor actor : actors) {
            list.add(FilmActor.builder().actor(actor).film(film).build());
        }
        return list;
    }
}