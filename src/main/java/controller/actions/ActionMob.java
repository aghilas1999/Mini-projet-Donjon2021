package controller.actions;

import model.Position;
import model.character.Monstre;
import model.character.Player;
import model.map.Map;
import view.ActionView;

public class ActionMob {

    Monstre monstre;
    boolean hasPlay= false;

    public ActionMob(Monstre monstre) {
        this.monstre = monstre;
    }

    public void play(Map map, Player player) {
        ActionView actionView= new ActionView(monstre,player);
        actionView.StartMob();
        //observation
        Position posJoueur = monstre.observation(player.position);
        if (posJoueur == null) {
            //déplacement
            monstre.move(map, player.getPosition());
            mobHasPlay();
            //pourquoi pas rajouter du heal en mode " se soigne " ?
        } else {
            //condition: le joueur est à distance de frappe
            if ( monstre.playerIsInRange(posJoueur))
            //attaquer
            {
                monstre.attackPlayer(player);
                actionView.PlayerAttack(player);
                mobHasPlay();
            } else
            // se diriger vers le joueur
            {
                monstre.follow(posJoueur, map);
                mobHasPlay();
            }
        }
    }

    private void mobHasPlay() {
        hasPlay = true;
    }

    public boolean hasPlay() {
        return hasPlay;
    }

    public void resetActions() {
        hasPlay = false;
    }
}
