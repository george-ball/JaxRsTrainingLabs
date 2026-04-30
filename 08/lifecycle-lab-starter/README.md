# Content and Lifecycle in JEE — Lab Starter Project

**Platform:** Java 11 LTS · Tomcat 9.0 · JAX-RS 2.1 (Jersey 2.41) · H2 2.2 · Maven 3.8+

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
cp target/lifecycle-lab.war $CATALINA_HOME/webapps/
$CATALINA_HOME/bin/startup.sh       # Windows: startup.bat

# 5. Verify
curl http://localhost:8080/lifecycle-lab/api/health
# Expected: {"status":"UP","service":"LifecycleLab","version":"1.0","timestamp":"..."}
```

---

## Project Structure

```
lifecycle-lab-starter/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/
    │   │   ├── api/
    │   │   │   ├── LifecycleApplication.java     ✅ Complete — @ApplicationPath("/api")
    │   │   │   ├── resource/
    │   │   │   │   ├── HealthResource.java        ✅ Complete — baseline verification
    │   │   │   │   ├── ConfigResource.java        🔧 Lab 1 — implement 4 endpoints
    │   │   │   │   ├── JndiResource.java          🔧 Lab 2 — implement 2 endpoints
    │   │   │   │   ├── ProductResource.java       🔧 Lab 3 — implement list() + create()
    │   │   │   │   ├── SecuredOrderResource.java  🔧 Lab 4 — add @Secured + implement create()
    │   │   │   │   └── AdminResource.java         🔧 Lab 4 — add role annotations
    │   │   │   ├── filter/
    │   │   │   │   ├── Secured.java               ✅ Complete — @NameBinding annotation
    │   │   │   │   └── BearerTokenFilter.java     🔧 Lab 4 — implement filter()
    │   │   │   └── security/
    │   │   │       ├── BearerSecurityContext.java ✅ Complete
    │   │   │       ├── UserPrincipal.java         ✅ Complete
    │   │   │       └── TokenStore.java            ✅ Complete (2 pre-registered tokens)
    │   │   └── listener/
    │   │       └── AppLifecycleListener.java      🔧 Labs 1 & 2 — add JNDI binding
    │   ├── resources/
    │   │   ├── messages.properties               ✅ Complete — classpath resource for Lab 1
    │   │   └── sql/init.sql                      ✅ Complete — H2 DDL + seed data
    │   └── webapp/
    │       ├── META-INF/context.xml              ✅ Complete — JNDI resources for Tomcat
    │       └── WEB-INF/
    │           ├── web.xml                       ✅ Complete — context-params + resource-ref
    │           └── templates/welcome.html        ✅ Complete — WAR file for Lab 1
    └── test/java/com/example/SmokeTest.java      ✅ 8 baseline tests — do not modify
```

---

## Lab Roadmap

| Lab | File(s) to complete | Key outcome |
|-----|---------------------|-------------|
| **Lab 1** | `ConfigResource`, `AppLifecycleListener` (logging only) | `GET /api/config/version` returns `"2.4.1"` from web.xml |
| **Lab 2** | `JndiResource`, `AppLifecycleListener` (JNDI binding) | `GET /api/jndi/greeting` returns JNDI-bound string |
| **Lab 3** | `ProductResource` | `GET /api/db/products` returns H2 data; transactional POST |
| **Lab 4** | `BearerTokenFilter`, `SecuredOrderResource`, `AdminResource` | 401/403/200 based on token and role |

---

## Pre-Registered Tokens (Lab 4)

| Token | Username | Roles |
|-------|----------|-------|
| `user-token` | alice | user |
| `admin-token` | bob | user, admin |

Use in curl with:  `-H 'Authorization: Bearer user-token'`

---

## URI Reference (after all labs complete)

```
# Lab 1
GET  /lifecycle-lab/api/health
GET  /lifecycle-lab/api/config/version       → "2.4.1" (web.xml init-param)
GET  /lifecycle-lab/api/config               → all three init-params as JSON
GET  /lifecycle-lab/api/config/template      → welcome.html from WEB-INF
GET  /lifecycle-lab/api/config/classpath     → messages.properties from classpath

# Lab 2
GET  /lifecycle-lab/api/jndi/version         → "2.4.1" (JNDI lookup)
GET  /lifecycle-lab/api/jndi/greeting        → "Welcome to 2.4.1" (bound at startup)

# Lab 3
GET  /lifecycle-lab/api/db/products          → JSON array from H2 PRODUCTS table
POST /lifecycle-lab/api/db/products          → transactional insert
GET  /lifecycle-lab/api/db/audit             → AUDIT_LOG table contents

# Lab 4
GET  /lifecycle-lab/api/secured/orders        → public (no token)
POST /lifecycle-lab/api/secured/orders        → requires Bearer token → 201
GET  /lifecycle-lab/api/admin/stats           → requires "admin" role → 200/403
GET  /lifecycle-lab/api/admin/ping            → @PermitAll → 200 always
GET  /lifecycle-lab/api/admin/locked          → @DenyAll → 403 always
GET  /lifecycle-lab/api/admin/reports         → requires "admin" or "manager"
```

---

## Common Problems

**`UnsupportedOperationException: TODO`**
Method not yet implemented — check which lab task covers it.

**`GET /api/config/version` returns null**
`app.version` `<context-param>` is in `web.xml`. Check spelling. Ensure WAR was redeployed after editing.

**`GET /api/jndi/version` returns 404 with "JNDI name not found"**
The `app.version` Environment entry is declared in `META-INF/context.xml`. Verify Tomcat loaded the file (check catalina.out). The JNDI name is `java:comp/env/app.version` (note the `java:comp/env/` prefix).

**`GET /api/jndi/greeting` throws `NameNotFoundException`**
`AppLifecycleListener.contextInitialized()` not yet implemented (Lab 2 Task 2.2), or the greeting was not bound. Check Tomcat log for `"JNDI resources bound."`.

**`GET /api/db/products` returns 500**
Check Tomcat log. Likely causes: H2 driver not on classpath (check pom.xml), SQL init script failed (check `sql/init.sql`), or DataSource lookup failed (check `META-INF/context.xml` JNDI name matches `java:comp/env/jdbc/shopDB`).

**Connection pool exhausted**
Pool has `maxTotal=5`. If you cause a connection leak (Lab 3 Q2), subsequent requests will block or fail. Redeploy the WAR to reset the pool.

**`POST /api/secured/orders` returns 200 instead of 401**
`BearerTokenFilter` not yet implemented (Lab 4 Task 4.1), or `@Secured` not added to the POST method (Lab 4 Task 4.2).

**`GET /api/admin/stats` with admin-token returns 403**
`RolesAllowedDynamicFeature` not registered in `LifecycleApplication`. Uncomment `getClasses()` in Lab 4 Task 4.3.

**Tests fail: `ClassNotFoundException`**
Run `mvn clean test` (not just `mvn test`) to clear stale compiled classes.
