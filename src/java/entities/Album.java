/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

/**
 *
 * @author matheusfernal
 */
public class Album {
    
    private String name;
    private String genre;
    private String singer;
    private String label;

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getSinger() {
        return singer;
    }

    public String getLabel() {
        return label;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
