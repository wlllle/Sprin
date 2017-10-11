/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hiber;

import stuff.*;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

/**
 *
 * @author slowen
 */
public class Hiber {
    public static void main(String[] args) throws SQLException {
        Collection persons = Factory.getInstance().getPersonDAO().getAllPersons();
        Iterator iterator = persons.iterator();
        System.out.println("========Все сотрудники========");
        while (iterator.hasNext()) {
            Person person = (Person)iterator.next();
            System.out.println("Имя : " + person.getFirstName() + "  Фамилия : " + person.getMiddleName());
            Collection subdivisions = person.getManagedSubdivisions();
            Iterator iterator2 = subdivisions.iterator();
            while (iterator2.hasNext()) {
                Subdivision subdivision = (Subdivision)iterator2.next();
                System.out.println("Начальник " + subdivision.getHeadPerson().getLastName());
                }
        }

        Collection subdivisions = Factory.getInstance().getSubdivisionDAO().getAllSubdivisions();
        iterator = subdivisions.iterator();
        System.out.println("========Все подразделения========");
        while (iterator.hasNext()) {
            Subdivision subdivision = (Subdivision) iterator.next();
            System.out.println("Подразделение : " + subdivision.getName());
            Collection SubdivisionPositions = subdivision.getSubdivisionPositions();
            Iterator iterator2 = SubdivisionPositions.iterator();
            while (iterator2.hasNext()) {
                SubdivisionsPositions subpos = (SubdivisionsPositions)iterator2.next();
                System.out.println("Начальник " + subpos.getPosition().getName());
                }
        }
    }
}
