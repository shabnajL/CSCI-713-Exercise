package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

class StudentServiceTest {

    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService();
    }

    @Test
    void testAddStudentAndTopStudent() {
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.9);

        service.addStudent(s1);
        service.addStudent(s2);

        // Test if top student is correctly identified
        Student top = service.getTopStudent();
        assertNotNull(top);
        assertEquals("Bob", top.getName());
    }

    @Test
    void testCalculateAverageGpa() {
        service.addStudent(new Student("Alice", 20, 3.5));
        service.addStudent(new Student("Bob", 22, 3.5));

        double avg = service.calculateAverageGpa();
        assertEquals(3.5, avg, 0.001);
    }

    
    // Intentionally leave out tests for:
    
    // - behavior with empty student list
    @Test
    void testCalculateAverageGpa_emptyList() {
        double avg = service.calculateAverageGpa();
        assertEquals(0.0, avg, 0.001);
    }

    // - removeStudentByName - success, empty list, not found
    @Test
    void testRemoveStudentByName() {
        Student s1 = new Student("Alice", 20, 3.5);
        Student s2 = new Student("Bob", 22, 3.5);

        service.addStudent(s1);
        service.addStudent(s2);

        boolean removed = service.removeStudentByName(s1.getName());
        assertTrue(removed);

        Student top = service.getTopStudent();
        assertEquals("Bob", top.getName());
    }

    // Empty list should return false
    @Test
    void testRemoveStudentByName_emptyList() {
        boolean removed = service.removeStudentByName("Alice");
        assertFalse(removed);
    }  
    // Not found should return false
    @Test
    void testRemoveStudentByName_notFound() {
        service.addStudent(new Student("Bob", 22, 3.5));

        boolean removed = service.removeStudentByName("Alice");
        assertFalse(removed);
    }  

    // - Utils methods - checkName, isValidAge
    @Test
    void testCheckName() {
        assertTrue(Utils.checkName("Alice"));
        assertFalse(Utils.checkName(null));
        assertFalse(Utils.checkName(""));
    }

    @Test
    void testIsValidAge() {
        assertTrue(Utils.isValidAge(20));
        assertFalse(Utils.isValidAge(-1));
    }
}
