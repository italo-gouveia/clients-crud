package br.com.snet.challenge.data.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 17)
    private String phone;

    @Column(nullable = false, length = 100)
    private String address;

    @Column(name = "house_number", nullable = false, length = 10)
    private String houseNumber;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Column(nullable = false, length = 30)
    private String country;

    @Column(name = "postal_code", nullable = false, length = 8)
    private String postalCode;

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return id.equals(client.id) &&
                name.equals(client.name) &&
                phone.equals(client.phone) &&
                address.equals(client.address) &&
                houseNumber.equals(client.houseNumber) &&
                city.equals(client.city) &&
                state.equals(client.state) &&
                country.equals(client.country) &&
                postalCode.equals(client.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, phone, address, houseNumber, city, state, country, postalCode);
    }
}
