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
    
    private String id;
    private String title;
    private String genre;
    private String artist;
    private String label;
    private Integer year;
    private String coverPath;
    private List<Track> tracks;
    private List<String> tags;
    private Collection collection;
    
    public Album(String title, String artist)
    {
        //TODO: Handle title or artist null
        this.title = title;
        this.artist = artist;
    }
    
    public String getTitle() {
        return title;
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

    public void setTitle(String title) {
        this.title = title;
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
    
        public Integer getYear()
    {
        return year;
    }

    public void setYear(Integer year)
    {
        this.year = year;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.title);
        hash = 61 * hash + Objects.hashCode(this.genre);
        hash = 61 * hash + Objects.hashCode(this.artist);
        hash = 61 * hash + Objects.hashCode(this.label);
        hash = 61 * hash + Objects.hashCode(this.year);
        hash = 61 * hash + Objects.hashCode(this.coverPath);
        hash = 61 * hash + Objects.hashCode(this.tracks);
        hash = 61 * hash + Objects.hashCode(this.tags);
        hash = 61 * hash + Objects.hashCode(this.collection);
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
        if (!Objects.equals(this.id, other.id))
        {
            return false;
        }
        if (!Objects.equals(this.title, other.title))
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
        if (!Objects.equals(this.year, other.year))
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
