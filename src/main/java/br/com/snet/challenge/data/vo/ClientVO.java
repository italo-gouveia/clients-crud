package br.com.snet.challenge.data.vo;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Objects;

@JsonPropertyOrder({ "id", "name", "phone", "address", "houseNumber", "city", "uf", "country", "postalCode" })
public class ClientVO extends ResourceSupport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Mapping("id")
    @JsonProperty("id")
    private Long key;
    private String name;
    private String phone;
    private String address;
    private String houseNumber;
    private String city;
    private String state;
    private String country;
    private String postalCode;

    public ClientVO() {
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
        if (!(o instanceof ClientVO)) return false;
        if (!super.equals(o)) return false;
        ClientVO clientVO = (ClientVO) o;
        return key.equals(clientVO.key) &&
                name.equals(clientVO.name) &&
                phone.equals(clientVO.phone) &&
                address.equals(clientVO.address) &&
                houseNumber.equals(clientVO.houseNumber) &&
                city.equals(clientVO.city) &&
                state.equals(clientVO.state) &&
                country.equals(clientVO.country) &&
                postalCode.equals(clientVO.postalCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key, name, phone, address, houseNumber, city, state, country, postalCode);
    }

}