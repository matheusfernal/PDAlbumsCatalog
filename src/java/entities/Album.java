/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author matheusfernal
 */
public class Album {
    
    private String name;
    private String genre;
    private String artist;
    private String label;
    private String coverPath;
    private List<Track> tracks;
    private List<String> tags;
    private Collection collection;
    
    public Album(String name, String artist)
    {
        this.name = name;
        this.artist = artist;
    }
    
    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getArtist() {
        return artist;
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

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Track> getTracks()
    {
        return tracks;
    }

    public void setTracks(List<Track> tracks)
    {
        this.tracks = tracks;
    }

    public List<String> getTags()
    {
        return tags;
    }

    public void setTags(List<String> tags)
    {
        this.tags = tags;
    }

    public Collection getCollection()
    {
        return collection;
    }

    public void setCollection(Collection collection)
    {
        this.collection = collection;
    }

    public String getCoverPath()
    {
        return coverPath;
    }

    public void setCoverPath(String coverPath)
    {
        this.coverPath = coverPath;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.name);
        hash = 13 * hash + Objects.hashCode(this.genre);
        hash = 13 * hash + Objects.hashCode(this.artist);
        hash = 13 * hash + Objects.hashCode(this.label);
        hash = 13 * hash + Objects.hashCode(this.coverPath);
        hash = 13 * hash + Objects.hashCode(this.tracks);
        hash = 13 * hash + Objects.hashCode(this.tags);
        hash = 13 * hash + Objects.hashCode(this.collection);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final Album other = (Album) obj;
        if (!Objects.equals(this.name, other.name))
        {
            return false;
        }
        if (!Objects.equals(this.genre, other.genre))
        {
            return false;
        }
        if (!Objects.equals(this.artist, other.artist))
        {
            return false;
        }
        if (!Objects.equals(this.label, other.label))
        {
            return false;
        }
        if (!Objects.equals(this.coverPath, other.coverPath))
        {
            return false;
        }
        if (!Objects.equals(this.tracks, other.tracks))
        {
            return false;
        }
        if (!Objects.equals(this.tags, other.tags))
        {
            return false;
        }
        if (!Objects.equals(this.collection, other.collection))
        {
            return false;
        }
        return true;
    }
}
