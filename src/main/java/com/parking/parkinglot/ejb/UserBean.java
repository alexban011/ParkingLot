package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.entities.User;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserBean {

    private static final Logger LOG = Logger.getLogger(UserBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers() {
        LOG.info("findAllUsers");
        try {
            TypedQuery<User> typedQuery =
                    entityManager.createQuery("SELECT c FROM User c", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        } catch (Exception ex) {
            throw new EJBException(ex);
        }
    }

    public List<UserDto> copyUsersToDto(List<User> users) {
        List<UserDto> userDto = new LinkedList<UserDto>();

        for (int i = 0; i < users.size(); i++) {
            UserDto dto = new UserDto(users.get(i).getId(),
                    users.get(i).getEmail(),
                    users.get(i).getPassword(),
                    users.get(i).getUsername());
            userDto.add(dto);
        }

        return userDto;
    }
}
