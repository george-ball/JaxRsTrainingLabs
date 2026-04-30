# Dispatching Requests — Lab Starter Project

**Platform:** Java 11 LTS · Tomcat 9.0 · JAX-RS 2.1 (Jersey 2.41) · Maven 3.8+

---

## Quick Start

```bash
# 1. Verify environment
java -version      # must be 11.x
mvn  -version      # must be 3.8+

# 2. Build
mvn clean package -DskipTests

# 3. Run baseline tests — all 10 must PASS before starting
mvn test -Dtest=SmokeTest

# 4. Deploy to Tomcat 9
cp target/dispatch-lab.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh       # Windows: startup.bat

# 5. Verify health endpoint
curl http://localhost:8080/dispatch-lab/api/health
# Expected: {"status":"UP","service":"DispatchLab","version":"1.0","timestamp":"..."}
```

---

## Project Structure

```
dispatch-lab-starter/
├── pom.xml
└── src/
    ├── main/java/com/example/api/
    │   ├── ShopApplication.java         🔧 Lab 1  — add getClasses()
    │   ├── model/
    │   │   ├── Product.java             ✅ Complete
    │   │   ├── Order.java               ✅ Complete
    │   │   ├── OrderLine.java           ✅ Complete
    │   │   ├── Store.java               ✅ Complete
    │   │   └── InventoryItem.java       ✅ Complete
    │   ├── repository/
    │   │   ├── ProductRepository.java   ✅ Complete (6 seed products)
    │   │   ├── OrderRepository.java     ✅ Complete (starts empty)
    │   │   └── StoreRepository.java     ✅ Complete (3 stores, 9 inventory items)
    │   ├── resource/
    │   │   ├── HealthResource.java      ✅ Complete — Lab 1 test target
    │   │   ├── ProductResource.java     🔧 Labs 2 & 3 — @Path templates + verbs
    │   │   ├── OrderResource.java       🔧 Lab 3 — CRUD + status codes
    │   │   ├── StoreResource.java       🔧 Lab 4 — sub-resource locator
    │   │   └── InventoryResource.java   🔧 Lab 4 — sub-resource class
    │   └── service/
    │       └── ProductService.java      🔧 Lab 5 — annotated interface
    └── test/java/com/example/api/
        └── SmokeTest.java              ✅ 10 baseline tests — do not modify
```

---

## Seed Data

### Products (ProductRepository — 6 items)

| ID   | NumId | SKU      | Name                  | Category    | Price  | Featured |
|------|-------|----------|-----------------------|-------------|--------|----------|
| P001 | 1     | BOOK-001 | Clean Code            | books       | 39.99  | ✓        |
| P002 | 2     | BOOK-002 | Effective Java        | books       | 49.99  | ✓        |
| P003 | 3     | BOOK-003 | Java 11 Pocket Guide  | books       | 24.99  |          |
| P004 | 4     | ELEC-001 | Mechanical Keyboard   | electronics | 129.99 | ✓        |
| P005 | 5     | ELEC-002 | USB-C Hub             | electronics | 34.99  |          |
| P006 | 6     | BOOK-004 | Old Java EE 5 Book    | books       | 9.99   | inactive |

### Stores (StoreRepository — 3 stores)

| ID        | Name               | City       | Inventory                              |
|-----------|--------------------|------------|----------------------------------------|
| STORE-001 | London Flagship    | London     | BOOK-001 (42), BOOK-002 (18), ELEC-001 (5)  |
| STORE-002 | Manchester Branch  | Manchester | BOOK-001 (12), ELEC-002 (50), BOOK-003 (25) |
| STORE-003 | Edinburgh Outlet   | Edinburgh  | BOOK-002 (8),  ELEC-001 (2),  ELEC-002 (30) |

---

## Lab Roadmap

| Lab | Focus | Key Task |
|-----|-------|----------|
| **Lab 1** | @ApplicationPath | Deploy, verify health, add getClasses(), create /api/v2 |
| **Lab 2** | @Path templates | Literals, variables, regex, tiebreakers, multi-segment |
| **Lab 3** | HTTP methods | CRUD verbs, status codes, content negotiation |
| **Lab 4** | Sub-resource locators | StoreResource locator → InventoryResource |
| **Lab 5** | Annotation inheritance | Interface contract → implementation |

---

## URI Reference (after all labs complete)

```
# Lab 1
GET  /dispatch-lab/api/health
GET  /dispatch-lab/api/v2/health

# Lab 2 — @Path patterns
GET  /dispatch-lab/api/products                         # all products
GET  /dispatch-lab/api/products/P001                    # by string id
GET  /dispatch-lab/api/products/4                       # by numeric id (regex \d+)
GET  /dispatch-lab/api/products/BOOK-001                # by SKU (regex [A-Z]+-\d+)
GET  /dispatch-lab/api/products/featured                # literal path (beats {id})
GET  /dispatch-lab/api/products/search?q=java
GET  /dispatch-lab/api/products/archive/2024/Q1

# Lab 3 — HTTP verbs
POST   /dispatch-lab/api/orders
GET    /dispatch-lab/api/orders/ORD-001
PUT    /dispatch-lab/api/orders/ORD-001
DELETE /dispatch-lab/api/orders/ORD-001
HEAD   /dispatch-lab/api/orders/ORD-001
GET    /dispatch-lab/api/products/P001/summary   (JSON or text/plain)

# Lab 4 — Sub-resource locator
GET  /dispatch-lab/api/stores
GET  /dispatch-lab/api/stores/STORE-001
GET  /dispatch-lab/api/stores/STORE-001/inventory
GET  /dispatch-lab/api/stores/STORE-001/inventory/BOOK-001
PUT  /dispatch-lab/api/stores/STORE-001/inventory/BOOK-001

# Lab 5 — same URIs as Lab 2/3 but via interface contract
GET  /dispatch-lab/api/v2/products
```

---

## Troubleshooting

**`UnsupportedOperationException: TODO`**
Method not yet implemented. Check which lab task covers it.

**`404` on a resource that should exist**
Either the class is not registered in `ShopApplication.getClasses()`, or
auto-scanning was disabled when you added `getClasses()`.

**Regex path not matching**
In Java string literals, backslash must be doubled: `"\\d+"` not `"\d+"`.
In `@Path`, the regex sits after a colon: `@Path("{id: \\d+}")`.

**405 Method Not Allowed (unexpected)**
A `@Path` exists but no method has the required HTTP verb annotation.
Check for a missing `@GET`, `@POST`, etc. — this is also the all-or-nothing rule at work.

**CDI injection `null` in InventoryResource**
The locator creates the instance with `new` — CDI cannot inject into it.
Use `@Context ResourceContext rc` in the locator, then call `rc.initResource(resource)`.

**Tests fail: ClassNotFoundException**
Run `mvn clean test` (not just `mvn test`) to clear stale class files.
