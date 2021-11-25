package model.object.weapons;

import model.character.CharacterInterface;
import model.object.ObjectInterface;

public interface WeaponInterface extends ObjectInterface {

    public int getRange();
    public boolean use(CharacterInterface entity);
}
