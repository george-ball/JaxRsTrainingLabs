# Parameter and Return Types — Lab Starter Project

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
cp target/params-lab.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh       # Windows: startup.bat

# 5. Verify
curl http://localhost:8080/params-lab/api/health
# Expected: {"status":"UP","service":"ParamsLab","version":"1.0","timestamp":"..."}
```

---

## Project Structure

```
params-lab-starter/
├── pom.xml
└── src/
    ├── main/java/com/example/api/
    │   ├── ParamsApplication.java          ✅ Complete — auto-scanning @ApplicationPath("/api")
    │   ├── model/
    │   │   ├── Product.java                ✅ Complete (with sku, numericId, featured)
    │   │   └── OrderStatus.java            ✅ Complete (NEW/PROCESSING/SHIPPED/DELIVERED/CANCELLED)
    │   ├── repository/
    │   │   └── ProductRepository.java      ✅ Complete (6 seed products)
    │   ├── storage/
    │   │   └── FileStorage.java            ✅ Complete (temp dir + seed files for Lab 5)
    │   ├── resource/
    │   │   ├── HealthResource.java         ✅ Complete — Lab baseline
    │   │   ├── ParamTestResource.java      🔧 SKELETON — Labs 1, 3, 4
    │   │   ├── ContentResource.java        🔧 SKELETON — Lab 2
    │   │   └── FileResource.java           🔧 SKELETON — Lab 5
    │   ├── converter/
    │   │   └── LocalDateConverterProvider.java  🔧 SKELETON — Lab 1 Task 1.4
    │   └── filter/
    │       └── ProductFilter.java          🔧 SKELETON — Lab 3 Task 3.3 (@BeanParam)
    └── test/java/com/example/api/
        └── SmokeTest.java                  ✅ 10 baseline tests — do not modify
```

---

## Lab Roadmap

| Lab | File(s) to complete | Key Task |
|-----|---------------------|----------|
| **Lab 1** | `ParamTestResource`, `LocalDateConverterProvider` | Primitive/boxed comparison, enum params, UUID, custom ParamConverter |
| **Lab 2** | `ContentResource` | @Produces class vs method, 415/406 errors, CSV negotiation |
| **Lab 3** | `ParamTestResource`, `ProductFilter` | All @Param sources in one endpoint, @FormParam, @BeanParam refactor |
| **Lab 4** | `ParamTestResource` | @DefaultValue matrix, Response builder (201/304/302), GenericEntity |
| **Lab 5** | `FileResource` | byte[]/InputStream upload, StreamingOutput, safeResolve(), path traversal |

---

## Seed Data

### Products (6 items, reset with `ProductRepository.getInstance().reset()`)

| ID   | NumId | SKU      | Name                 | Category    | Price  | Featured |
|------|-------|----------|----------------------|-------------|--------|----------|
| P001 | 1     | BOOK-001 | Clean Code           | books       | 39.99  | ✓        |
| P002 | 2     | BOOK-002 | Effective Java       | books       | 49.99  | ✓        |
| P003 | 3     | BOOK-003 | Java 11 Pocket Guide | books       | 24.99  |          |
| P004 | 4     | ELEC-001 | Mechanical Keyboard  | electronics | 129.99 | ✓        |
| P005 | 5     | ELEC-002 | USB-C Hub            | electronics | 34.99  |          |
| P006 | 6     | BOOK-004 | Old Java EE 5 Book   | books       | 9.99   | inactive |

### File Storage (seed files, reset with `FileStorage.getInstance().reset()`)
- `hello.txt` — plain text file for download testing
- `data.csv`  — CSV file for MIME detection testing

FileStorage uses a temporary directory created at JVM startup. The path is available via `FileStorage.getInstance().getBaseDir()`.

---

## URI Reference (after all labs complete)

```
# Health (baseline)
GET  /params-lab/api/health

# Lab 1 — Simple types
GET  /params-lab/api/params/primitive?count=5
GET  /params-lab/api/params/boxed?count=5
GET  /params-lab/api/params/orders?status=NEW
GET  /params-lab/api/params/items/550e8400-e29b-41d4-a716-446655440000
GET  /params-lab/api/params/events?from=2024-01-01&to=2024-12-31

# Lab 2 — Content negotiation
GET  /params-lab/api/content/json
GET  /params-lab/api/content/text
POST /params-lab/api/content/ingest
GET  /params-lab/api/content/products      (Accept: text/csv or application/json)

# Lab 3 — @XXXParam
GET  /params-lab/api/params/diagnostic/ITEM-001;version=2?filter=active
POST /params-lab/api/params/login
GET  /params-lab/api/params/products?category=books&page=0&size=20

# Lab 4 — @DefaultValue + Response builder
GET  /params-lab/api/params/defaults
POST /params-lab/api/params/products
GET  /params-lab/api/params/products/P001/etag
GET  /params-lab/api/params/redirect?to=P001
GET  /params-lab/api/params/typed

# Lab 5 — Binary + file delivery
GET  /params-lab/api/files
POST /params-lab/api/files/upload/bytes?name=myfile.txt
POST /params-lab/api/files/upload/stream?name=myfile.txt
GET  /params-lab/api/files/hello.txt
GET  /params-lab/api/files/hello.txt?inline=true
```

---

## Common Problems

**`UnsupportedOperationException: TODO`**
Method not yet implemented — check which lab task covers it.

**LocalDate @QueryParam causes 404 or 500**
`LocalDateConverterProvider` not yet implemented (Lab 1 Task 1.4), or not registered. Since `ParamsApplication` uses auto-scanning, adding `@Provider` to the class is sufficient.

**@BeanParam fields are null**
Check the field annotations in `ProductFilter` — each field needs its own `@QueryParam` / `@HeaderParam` etc. annotation.

**415 on POST when you expect 200**
Your Content-Type header doesn't match `@Consumes`. Check both the curl `-H` flag and the `@Consumes` value.

**Path traversal test returns 200 instead of 400/403**
`safeResolve()` not yet implemented (Lab 5 Task 5.3). Implement it before testing security.

**StreamingOutput throws IOException mid-stream**
The client disconnected. JAX-RS catches `WebApplicationException` from within `StreamingOutput.write()` — wrap `IOException` in it if you need the runtime to handle it cleanly.

**Tests fail: ClassNotFoundException**
Run `mvn clean test` (not just `mvn test`) to clear stale class files.
