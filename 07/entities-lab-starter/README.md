# JEE Entities and Complex Content — Lab Starter Project

**Platform:** Java 11 LTS · Tomcat 9.0 · JAX-RS 2.1 (Jersey 2.41) · JAXB 2.3 · Maven 3.8+

---

## Quick Start

```bash
# 1. Verify environment
java -version      # must be 11.x
mvn  -version      # must be 3.8+

# 2. Build
mvn clean package -DskipTests

# 3. Run baseline tests — all 8 must PASS before starting
mvn test -Dtest=SmokeTest

# 4. Deploy to Tomcat 9
cp target/entities-lab.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh       # Windows: startup.bat

# 5. Verify
curl http://localhost:8080/entities-lab/api/health
# Expected: {"status":"UP","service":"EntitiesLab","version":"1.0","timestamp":"..."}
```

---

## Project Structure

```
entities-lab-starter/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/
    │   │   ├── api/
    │   │   │   ├── EntitiesApplication.java   ✅ Complete — @ApplicationPath("/api")
    │   │   │   ├── HealthResource.java        ✅ Complete — baseline verification
    │   │   │   ├── ProductResource.java       ✅ Complete — CSV endpoints wired
    │   │   │   └── OrderResource.java         ✅ Complete — dual JSON/XML endpoints
    │   │   ├── model/
    │   │   │   ├── Product.java               ✅ Complete
    │   │   │   ├── Order.java                 🔧 Lab 3 — add JAXB annotations
    │   │   │   ├── OrderLine.java             🔧 Lab 3 — add JAXB annotations
    │   │   │   ├── Address.java               🔧 Lab 3 — add JAXB annotations
    │   │   │   └── OrderStatus.java           ✅ Complete
    │   │   ├── adapter/
    │   │   │   ├── LocalDateAdapter.java      ✅ Complete (LocalDate ↔ "yyyy-MM-dd")
    │   │   │   └── CurrencyAdapter.java       ✅ Complete (BigDecimal ↔ "dd.dd")
    │   │   ├── repository/
    │   │   │   ├── ProductRepository.java     ✅ Complete (6 seed products)
    │   │   │   └── OrderRepository.java       ✅ Complete (2 seed orders)
    │   │   └── provider/
    │   │       ├── ProductCsvWriter.java      🔧 Lab 1 Task 1.1 — implement
    │   │       ├── ProductCsvReader.java      🔧 Lab 1 Task 1.2 — implement
    │   │       ├── JacksonObjectMapperProvider.java  🔧 Lab 2 — implement
    │   │       └── ValidatingOrderReader.java 🔧 Lab 4 — implement
    │   ├── resources/xsd/
    │   │   └── order-schema.xsd              ✅ Complete — used by Lab 4 validator
    │   ├── webapp/WEB-INF/
    │   └── xsd/
    │       └── (empty — write catalogue-schema.xsd here in Lab 4)
    └── test/
        ├── java/com/example/SmokeTest.java   ✅ 8 baseline tests — do not modify
        └── resources/xml/valid-order.xml     ✅ Complete — Lab 4 curl test file
```

---

## Lab Roadmap

| Lab | What you implement | Key outcome |
|-----|-------------------|-------------|
| **Lab 1** | `ProductCsvWriter` + `ProductCsvReader` | `GET /products/P001` with `Accept: text/csv` returns CSV |
| **Lab 2** | `JacksonObjectMapperProvider` | `orderDate` appears as `"2024-06-15"` not `[2024,6,15]` |
| **Lab 3** | JAXB annotations on `Order`, `OrderLine`, `Address` | `GET /orders/ORD-001` with `Accept: application/xml` returns valid XML |
| **Lab 4** | `catalogue-schema.xsd` → xjc → `CatalogueResource`; `ValidatingOrderReader` | Schema validation returns 400 for invalid XML; XXE prevented |

---

## Seed Data

### Products (6 items)
| ID   | Name                  | Category    | Price  |
|------|-----------------------|-------------|--------|
| P001 | Clean Code            | books       | 39.99  |
| P002 | Effective Java        | books       | 49.99  |
| P003 | Java 11 Pocket Guide  | books       | 24.99  |
| P004 | Mechanical Keyboard   | electronics | 129.99 |
| P005 | USB-C Hub             | electronics | 34.99  |
| P006 | Old Java EE 5 Book    | books       | 9.99   |

### Orders (2 items)
| ID      | Customer   | Status     | Lines                      | Total |
|---------|------------|------------|----------------------------|-------|
| ORD-001 | Jane Smith | PROCESSING | BK-001 ×2, BK-002 ×1       | 99.97 |
| ORD-002 | Bob Jones  | NEW        | ELEC-002 ×1                | 34.99 |

ORD-001 also has `internalRef = "INTERNAL-REF-001"` which must NOT appear in XML output (Lab 3).

---

## Lab 4 — xjc Setup

To enable xjc in Lab 4:

1. Write `src/main/xsd/catalogue-schema.xsd`
2. Uncomment the `jaxb2-maven-plugin` block in `pom.xml`
3. Set `<packageName>com.example.library.generated</packageName>`
4. Run: `mvn generate-sources`
5. Mark `target/generated-sources/jaxb/` as a Generated Sources Root in your IDE

---

## Common Problems

**`UnsupportedOperationException: TODO`**
Method not yet implemented — check which lab task covers it.

**`GET /products/P001` with `Accept: text/csv` returns JSON (not CSV)**
`ProductCsvWriter` not yet implemented or `isWriteable()` returns false. Lab 1 Task 1.1.

**`orderDate` appears as `[2024,6,15]` in JSON**
`JacksonObjectMapperProvider` not yet implemented, or `JavaTimeModule` not registered. Lab 2.

**`GET /orders/ORD-001` with `Accept: application/xml` returns 500 or empty**
`Order` not yet annotated with `@XmlRootElement`. Lab 3 Task 3.1.

**`POST /orders/xml` does not validate (accepts any XML)**
`ValidatingOrderReader` not yet implemented. Lab 4 Task 4.4.

**`NullPointerException` in `ValidatingOrderReader` static initialiser**
`CTX` or `SCHEMA` is still null. Complete the TODO in the static block first.

**xjc fails: "Source does not exist"**
Ensure `src/main/xsd/catalogue-schema.xsd` exists before running `mvn generate-sources`.
