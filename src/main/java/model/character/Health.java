package model.character;

public class Health {

    private int max;
    private int actual;

    private int effect;

    public Health(){
        actual = 20;
        max = 20;
        //@TODO
    }
    public boolean isDead(){
        return actual==0;
    }

    public int getActual(){
        return actual;
    }

    public void remove(int damage){
        actual= actual- damage;
        if(actual<0)
            actual=0;
    }

    public void add(int heal){
        if(actual+heal>max)
            actual = max;
        else
            actual = actual+ heal;
    }

    public boolean isAlive(){return  ! isDead(); }


    public void setHeal(int i) {
        actual = i;
        if(actual<0)
            actual=0;
    }

    public void resurrect() {
        setHeal(max);
    }
}
