package project.commands;

import project.ProiectPOO;


/*
* Abstract class for commands using the Command design pattern.
* Undo implementation is not required.
*/
public abstract class Command {
    protected ProiectPOO proiect;

    public Command(ProiectPOO proiect) {
        this.proiect = proiect;
    }

    public abstract void execute(String[] params);

//    public abstract void undo(String[] params);
}
