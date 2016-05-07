package ua.softserveinc.tc.entity;

import org.hibernate.annotations.GenericGenerator;
import ua.softserveinc.tc.entity.ColumnConstants.CityConst;
import ua.softserveinc.tc.entity.ColumnConstants.RoomConst;
import ua.softserveinc.tc.entity.ColumnConstants.UserConst;

import javax.persistence.*;

/**
 * Created by Chak on 30.04.2016.
 */
@Entity
@Table(name = RoomConst.TABLE_NAME_ROOMS)
public class Room {
    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    @Column(name = RoomConst.ID_ROOM, unique = true, nullable = false)
    private Long id;

    @Column(name = RoomConst.NAME_ROOM)
    private String name;

    @Column(name = RoomConst.ADDRESS_ROOM)
    private String address;
    @Column(name = RoomConst.PHONE_ROOM)
    private String phoneNumber;
    @Column(name = RoomConst.CAPACITY_ROOM)
    private Integer capacity;
    @OneToOne
    @JoinColumn(name = UserConst.ID_USER)
    private User manager;
    @ManyToOne
    @JoinColumn(name = CityConst.ID_CITY)
    private City city;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }
}