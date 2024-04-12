public class Kalenders {
    private final Kalender [] kalenders;
    private int elementsNumber;
    public Kalenders(int size) {
        kalenders = new Kalender[size];
        elementsNumber = 0;
    }

    /**A method to add new kalender.
     *
     * @param kalender the kalendar to add to the list.
     * @return Zero for success, one when this kalender name is taken, two when the kalender collection is full.
     */
    public int addKalender(Kalender kalender) {
        if (elementsNumber == kalenders.length) {
            return 2;
        }
        for (int i = 0; i < elementsNumber; i++) {
            if(kalender.getName().equals(kalenders[i].getName())) {
                return 1;
            }
        }
        kalenders[elementsNumber] = kalender;
        elementsNumber++;
        return 0;
    }

    /**A method to rename a kalender name to another one, if this name already exists in the list, one or two will be returned.
     *
     * @param newName The name to rename to.
     * @param index The number of the calendar.
     * @return True by success, False when the new name is already taken.
     */
    public boolean renameKalender(String newName,int index) {
        if (newName==null||newName.isEmpty()){
            throw new IllegalArgumentException("Name cant be null or empty!");
        }
        for (Kalender temp: kalenders) {
            if(temp == null){
                break;
            }
            if (temp.getName().equals(newName)) {
                return false;
            }
        }
        kalenders[index].setName(newName);
        return true;
    }
    /** A method to remove a kalender by its given name.
     *
     * @param name The name of the kalender to remove.
     * @return true when the name of the kalender exits, false otherwise.
     */
    public void removeKalender(String name) {
        if (name==null||name.isEmpty()){
            throw new IllegalArgumentException("Name cant be null or empty!");
        }
        for (int i = 0; i < elementsNumber; i++) {
            if(kalenders[i].getName().equals(name)) {
                kalenders[i] = null;
                for(int j = i; j < elementsNumber - 1; j++) {
                    kalenders[j] = kalenders[j+1];
                }
                elementsNumber--;
            }
        }
    }

    Kalender[] getKalenders() {
        return kalenders;
    }

    int getElementsNumber() {
        return elementsNumber;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < elementsNumber; i++) {
            stringBuilder.append("\t").append((i + 1)).append(".").append("\t").append(kalenders[i].toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
