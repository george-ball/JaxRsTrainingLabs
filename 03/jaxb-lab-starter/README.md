# JAXB Lab Starter Project

**Platform:** Java 11 LTS · Tomcat 9.0 · JAXB 2.3 (`jakarta.xml.bind`) · Maven 3.8+

---

## Quick Start

```bash
# 1. Verify your environment
java -version      # must report 11.x
mvn  -version      # must report 3.8+

# 2. Build (skip tests on first run — no annotations yet)
mvn clean package -DskipTests

# 3. Run baseline smoke tests — all should PASS before you start
mvn test -Dtest=SmokeTest

# Expected: 13 tests pass, 0 failures
```

If all 13 smoke tests pass, your environment is correctly configured and
you are ready to begin Lab 1.

---

## Project Structure

```
jaxb-lab-starter/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/com/example/
│   │   │   ├── model/
│   │   │   │   ├── Order.java          🔧 SKELETON — annotate in Lab 2
│   │   │   │   ├── OrderLine.java      🔧 SKELETON — annotate in Lab 2
│   │   │   │   ├── Address.java        🔧 SKELETON — annotate in Lab 2
│   │   │   │   └── OrderStatus.java    🔧 SKELETON — annotate in Lab 2
│   │   │   ├── adapter/
│   │   │   │   ├── CurrencyAdapter.java ✅ PROVIDED — do not modify
│   │   │   │   └── LocalDateAdapter.java 🔧 SKELETON — implement in Lab 2
│   │   │   ├── xml/
│   │   │   │   └── OrderXmlUtil.java   🔧 SKELETON — implement in Lab 3
│   │   │   ├── batch/
│   │   │   │   └── OrderBatchProcessor.java 🔧 SKELETON — implement in Lab 4
│   │   │   ├── web/
│   │   │   │   ├── JaxbContextListener.java 🔧 SKELETON — implement in Lab 4
│   │   │   │   └── OrderServlet.java   🔧 SKELETON — implement in Lab 4
│   │   │   └── util/
│   │   │       └── OrderFixtures.java  ✅ PROVIDED — test data factory
│   │   ├── resources/
│   │   │   └── xsd/
│   │   │       └── order-schema.xsd    ✅ PROVIDED — used in Labs 3 & 4
│   │   ├── webapp/WEB-INF/
│   │   └── xsd/
│   │       └── README.md              ← write library.xsd here (Lab 1)
│   └── test/
│       ├── java/com/example/
│       │   ├── SmokeTest.java          ✅ PROVIDED — baseline, do not modify
│       │   └── xml/
│       │       └── OrderAnnotationTest.java 🔧 SKELETON — implement in Lab 3
│       └── resources/
│           ├── xsd/order-schema.xsd   ✅ PROVIDED — test classpath copy
│           └── xml/sample-order.xml   ✅ PROVIDED — for curl testing
```

---

## What is Pre-built vs What You Build

| File | Status | Lab |
|------|--------|-----|
| `CurrencyAdapter.java` | ✅ Complete | All labs |
| `OrderFixtures.java` | ✅ Complete | Labs 2–4 |
| `order-schema.xsd` (resources) | ✅ Complete | Labs 3–4 |
| `sample-order.xml` (test) | ✅ Complete | Lab 4 curl tests |
| `SmokeTest.java` | ✅ Complete — do not modify | Baseline |
| `library.xsd` | 🔧 You write from scratch | Lab 1 |
| `bindings.xjb` | 🔧 You write from scratch | Lab 1 extension |
| `Order.java` | 🔧 Add JAXB annotations | Lab 2 |
| `OrderLine.java` | 🔧 Add JAXB annotations | Lab 2 |
| `Address.java` | 🔧 Add JAXB annotations | Lab 2 |
| `OrderStatus.java` | 🔧 Add JAXB annotations | Lab 2 |
| `LocalDateAdapter.java` | 🔧 Implement marshal/unmarshal | Lab 2 |
| `OrderXmlUtil.java` | 🔧 Implement all methods | Lab 3 |
| `OrderAnnotationTest.java` | 🔧 Implement all @Test methods | Lab 3 |
| `JaxbContextListener.java` | 🔧 Implement contextInitialized | Lab 4 |
| `OrderServlet.java` | 🔧 Implement doPost + doGet | Lab 4 |
| `OrderBatchProcessor.java` | 🔧 Implement process() | Lab 4 |

---

## Lab Roadmap

| Lab | Focus | Key Task |
|-----|-------|----------|
| **Lab 1** | Schema-first path | Write `library.xsd`, run `mvn generate-sources`, inspect generated classes |
| **Lab 2** | Java-first path | Annotate `Order`, `OrderLine`, `Address`, `OrderStatus`; write `LocalDateAdapter` |
| **Lab 3** | Marshal/Unmarshal | Implement `OrderXmlUtil`; write JUnit 5 test suite |
| **Lab 4** | Tomcat integration | `JaxbContextListener`, `OrderServlet`, `OrderBatchProcessor` (StAX) |

---

## Lab 1 — Schema-first: xjc via Maven

```bash
# After writing src/main/xsd/library.xsd:
mvn generate-sources

# Generated classes appear here:
ls target/generated-sources/jaxb/com/example/library/model/
```

The plugin is configured in `pom.xml` under `jaxb2-maven-plugin`.
To enable a binding customisation file (Lab 1 Task 1.5),
uncomment the `<bindingFiles>` block in `pom.xml`.

---

## Lab 4 — Deploy to Tomcat 9

```bash
# Build the WAR
mvn clean package -DskipTests

# Deploy
cp target/jaxb-lab.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh      # Windows: startup.bat

# POST a sample order
curl -X POST http://localhost:8080/jaxb-lab/api/orders \
     -H 'Content-Type: application/xml' \
     -d @src/test/resources/xml/sample-order.xml

# GET it back
curl http://localhost:8080/jaxb-lab/api/orders/ORD-2024-001

# Undeploy cleanly
$CATALINA_HOME/bin/shutdown.sh
```

---

## Target XML (Labs 2 & 3)

After completing Lab 2 annotations, marshalling `OrderFixtures.createSampleOrder()`
should produce:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<order xmlns="http://example.com/shop/orders"
       orderId="ORD-2024-001"
       status="PROCESSING">
  <customerName>Jane Smith</customerName>
  <customerEmail>jane@example.com</customerEmail>
  <orderDate>2024-06-15</orderDate>
  <deliveryAddress>
    <street>10 Downing Street</street>
    <city>London</city>
    <postCode>SW1A 2AA</postCode>
    <country>GB</country>
  </deliveryAddress>
  <lines>
    <line sku="BK-001">
      <productName>Clean Code</productName>
      <quantity>2</quantity>
      <unitPrice>29.99</unitPrice>
      <lineTotal>59.98</lineTotal>
    </line>
    <line sku="BK-002">
      <productName>Effective Java</productName>
      <quantity>1</quantity>
      <unitPrice>39.99</unitPrice>
      <lineTotal>39.99</lineTotal>
    </line>
  </lines>
  <totalAmount>99.97</totalAmount>
</order>
```

Note: `internalRef` must NOT appear in the output.

---

## Common Problems

**`UnsupportedOperationException: TODO`**  
You called a method that has not been implemented yet. Check which Lab task covers it.

**`JAXBException: unable to marshal type "com.example.model.Order"`**  
The `@XmlRootElement` annotation is missing or has the wrong namespace.
Check Order.java.

**`JAXBException: unexpected element`**  
The namespace in your XML does not match the namespace in `@XmlSchema` / `@XmlRootElement`.
Both must be `http://example.com/shop/orders`.

**`NullPointerException` in static initialiser**  
`OrderXmlUtil.CTX` or `SCHEMA` is still null (the TODO stubs).
Complete Lab 3 Task 3.1 first.

**`ClassNotFoundException: jakarta.xml.bind.JAXBContext`**  
The JAXB API jar is not on the classpath.
Run `mvn dependency:tree | grep jaxb` — you should see `jakarta.xml.bind-api`.

**Tests fail — `order-schema.xsd` not found**  
The schema must be in `src/test/resources/xsd/order-schema.xsd`
(already provided). Check it exists and re-run `mvn test`.

**Generated xjc classes not visible in IDE**  
Mark `target/generated-sources/jaxb` as a Generated Sources Root in your IDE,
or run `mvn generate-sources` first and then reimport the Maven project.
