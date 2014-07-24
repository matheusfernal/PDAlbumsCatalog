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
public class Track
{
    private short number;
    private String name;

    public Track(short number, String name)
    {
        this.number = number;
        this.name = name;
    }

    public short getNumber()
    {
        return number;
    }

    public void setNumber(short number)
    {
        this.number = number;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }  
}
