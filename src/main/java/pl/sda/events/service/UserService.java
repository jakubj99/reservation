package pl.sda.events.service;


import pl.sda.events.dao.UserDao;
import pl.sda.events.model.AppUser;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import java.util.Optional;

@LocalBean
@Singleton
public class UserService {

    @EJB
    private UserDao userDao;

    public boolean checkUsernameNotExist(String username) {
        if (userDao.checkIfUserNameExists(username).isPresent()) {
            return false;
        }

        return true;
    }

    public boolean checkEmailNotExist(String email) {
        if (userDao.checkIfEmailExists(email).isPresent()) {
            return false;
        }

        return true;
    }

    public void register(AppUser appUser) {
        userDao.add(appUser);
    }

    public Optional<Long> login(String login, String password) {
        Optional<AppUser> appUserOptional = userDao.getUserWithLoginAndPassword(login, password);
        if (appUserOptional.isPresent()) {
            return Optional.of(appUserOptional.get().getId());
        }
        return Optional.empty();
    }

    public Optional<AppUser> findUserById(long owner_id) {
        return userDao.findUserWithId(owner_id);
    }

}
