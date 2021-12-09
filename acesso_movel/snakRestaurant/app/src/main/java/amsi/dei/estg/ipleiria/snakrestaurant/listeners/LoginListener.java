package amsi.dei.estg.ipleiria.snakrestaurant.listeners;

import amsi.dei.estg.ipleiria.snakrestaurant.models.Login;

public interface LoginListener {
    void onValidateLogin(final Login login);
}
