### **Learning Objectives**
- **Class Design:** Understand and practice the importance of designing closely related classes, including their interactions and dependencies.
- **Abstraction:** Learn to use abstract classes and methods to create a flexible base for various implementations.
- **Inheritance:** Implement inheritance by deriving subclasses from a base class, using both shared and specific behaviors.
- **Encapsulation:** Understand the significance of private fields and public methods in managing class data.
- **Problem Solving:** Apply concepts like object relationships, data manipulation, and calculations within a structured framework.

---

### **Computer Science Concepts Covered**
- **Object-Oriented Programming (OOP):**
  - Abstract classes
  - Inheritance
  - Encapsulation
- Array manipulation and shallow copies.
- Overriding methods (e.g., `getCost`, `equals`).
- Exception handling (e.g., `IllegalArgumentException`, `IllegalStateException`).
- Basic financial computations (e.g., tax calculations, currency formatting).
- Testing code robustness against edge cases.

---

### **Summary of the Problem to Solve**
The problem involves simulating an **online marketplace** by designing a set of interrelated classes. Project must implement:
1. **Products** (`MarketProduct` and its subclasses: `Egg`, `Fruit`, `Jam`, `SeasonalFruit`).
   - Each subclass calculates the cost of the product differently, reflecting real-world pricing models.
2. **Basket**: A collection of products with methods for adding, removing, calculating subtotals, taxes, and generating receipts.
3. **Customer**: Represents a user with a balance and a basket. The customer must have sufficient funds to check out their basket.

The solution requires creating a functional and robust class hierarchy that ensures:
- Proper calculation of costs and taxes.
- Valid operations on product collections (e.g., adding, removing, clearing).
- Generating detailed receipts for transactions.
