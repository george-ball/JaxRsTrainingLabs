# JAX-RS Lab Starter Project

**Platform:** Java 11 LTS · Tomcat 9.0 · JAX-RS 2.1 (Jersey 2.41) · Maven 3.8+

---

## Quick Start

```bash
# 1. Verify your environment
java -version      # must report 11.x
mvn  -version      # must report 3.8+

# 2. Build (skipping tests on first run)
mvn clean package -DskipTests

# 3. Deploy to Tomcat 9
cp target/jaxrs-lab.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh          # Windows: startup.bat

# 4. Verify the health endpoint
curl http://localhost:8080/jaxrs-lab/api/health

# Expected: {"status":"UP","service":"ShopAPI","version":"1.0","timestamp":"..."}

# 5. Run baseline tests (no Tomcat needed — in-memory container)
mvn test
```

---

## Project Structure

```
jaxrs-lab-starter/
├── pom.xml                          ← Maven build (Java 11, Jersey 2.41)
└── src/
    ├── main/
    │   └── java/com/example/api/
    │       ├── ShopApplication.java       ← JAX-RS Application entry point (Lab 1)
    │       ├── model/
    │       │   ├── Product.java           ← Domain model (complete — do not modify)
    │       │   └── Review.java            ← Domain model (complete — do not modify)
    │       ├── repository/
    │       │   ├── ProductRepository.java ← In-memory store (complete — do not modify)
    │       │   └── ReviewRepository.java  ← In-memory store (complete — do not modify)
    │       ├── resource/
    │       │   ├── HealthResource.java    ← Complete and working (Lab 1 test target)
    │       │   ├── ProductResource.java   ← SKELETON — complete in Labs 2 and 3
    │       │   └── ReviewResource.java    ← SKELETON — complete in Lab 3
    │       └── provider/
    │           └── Providers.java         ← SKELETON — complete in Lab 4
    └── test/
        └── java/com/example/api/
            └── StarterProjectSmokeTest.java ← Baseline tests — should all pass
```

---

## What is Pre-built vs What You Build

| File | Status | Used In |
|------|--------|---------|
| `Product.java` | ✅ Complete | All labs |
| `Review.java` | ✅ Complete | Lab 3 |
| `ProductRepository.java` | ✅ Complete | All labs |
| `ReviewRepository.java` | ✅ Complete | Lab 3 |
| `HealthResource.java` | ✅ Complete | Lab 1 |
| `ShopApplication.java` | 🔧 Skeleton | Labs 1, 4 |
| `ProductResource.java` | 🔧 Skeleton | Labs 2, 3 |
| `ReviewResource.java` | 🔧 Skeleton | Lab 3 |
| `Providers.java` | 🔧 Skeleton | Lab 4 |

---

## Seed Data

The `ProductRepository` is pre-loaded with six products:

| ID   | Name                  | Category    | Price  | Stock | Active |
|------|-----------------------|-------------|--------|-------|--------|
| P001 | Clean Code            | books       | 39.99  | 50    | true   |
| P002 | Effective Java        | books       | 49.99  | 30    | true   |
| P003 | Java 11 Pocket Guide  | books       | 24.99  | 75    | true   |
| P004 | Mechanical Keyboard   | electronics | 129.99 | 15    | true   |
| P005 | USB-C Hub             | electronics | 34.99  | 200   | true   |
| P006 | Old Java EE 5 Book    | books       | 9.99   | 5     | false  |

Call `ProductRepository.getInstance().reset()` in tests to restore this data.

---

## Full URI Structure (after you implement everything)

```
# Lab 1
GET  /jaxrs-lab/api/health

# Lab 2 — CRUD
GET  /jaxrs-lab/api/products
GET  /jaxrs-lab/api/products?category=books&page=0&size=10
GET  /jaxrs-lab/api/products/{id}
GET  /jaxrs-lab/api/products/{id}/summary    (JSON or text/plain)
POST /jaxrs-lab/api/products
PUT  /jaxrs-lab/api/products/{id}
DEL  /jaxrs-lab/api/products/{id}            (requires Bearer token after Lab 4)

# Lab 3 — Sub-resources
GET  /jaxrs-lab/api/products/{id}/stock
PUT  /jaxrs-lab/api/products/{id}/stock
PUT  /jaxrs-lab/api/products/{id}/activate
PUT  /jaxrs-lab/api/products/{id}/deactivate
GET  /jaxrs-lab/api/products/{id}/reviews
GET  /jaxrs-lab/api/products/{id}/reviews/{rid}
POST /jaxrs-lab/api/products/{id}/reviews
```

---

## Common Problems

**`java.lang.UnsupportedOperationException: TODO`**
You called an endpoint that has not been implemented yet. Check the lab task.

**`404 Not Found` on `/api/health`**
- Check Tomcat started without errors: `tail -f $CATALINA_HOME/logs/catalina.out`
- Verify the WAR was copied: `ls $CATALINA_HOME/webapps/jaxrs-lab.war`
- Confirm the WAR was exploded: `ls $CATALINA_HOME/webapps/jaxrs-lab/`

**`NoClassDefFoundError: jakarta/ws/rs/...`**
Jersey is not on the classpath. Run `mvn dependency:tree` and check jersey-container-servlet is listed.

**Tests fail with `ClassNotFoundException`**
Run `mvn clean test` (not just `mvn test`) to ensure stale classes are removed.
