package com.example.api.resource;

import com.example.api.storage.FileStorage;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.*;

import java.io.IOException;
import java.io.InputStream;


/**
 * File upload and download resource.  Students implement this in Lab 5.
 *
 * Base URI: /api/files
 *
 * ─────────────────────────────────────────────────────────────────────────────
 * LAB 5 — Tasks 5.1, 5.2, 5.3
 *
 * Task 5.1 — Upload endpoints (byte[] and InputStream)
 * Task 5.2 — Streaming download with StreamingOutput
 * Task 5.3 — safeResolve() — path traversal prevention
 *
 * SECURITY REQUIREMENT:
 *   ALL file path resolution MUST go through safeResolve().
 *   Never resolve user-supplied filenames directly.
 * ─────────────────────────────────────────────────────────────────────────────
 */
@Path("/files")
public class FileResource {

    private static final java.nio.file.Path BASE_DIR;
    static {
        BASE_DIR = FileStorage.getInstance().getBaseDir().normalize();
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 5 — Task 5.1a: Upload via byte[]
    // POST /api/files/upload/bytes?name=myfile.txt
    // Content-Type: application/octet-stream
    // ══════════════════════════════════════════════════════════════════════
    @POST
    @Path("upload/bytes")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response uploadBytes(
            @QueryParam("name") String name,
            byte[] data,
            @Context UriInfo uriInfo) {
        // TODO:
        //   1. Validate 'name' is not null/blank → 400 if missing
        //   2. FileStorage.getInstance().saveBytes(name, data)
        //   3. Build Location: uriInfo.getBaseUriBuilder().path("/api/files/{name}").build(name)
        //   4. Return 201 Created with Location header
        throw new UnsupportedOperationException("TODO — Lab 5 Task 5.1");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 5 — Task 5.1b: Upload via InputStream (streaming)
    // POST /api/files/upload/stream?name=myfile.txt
    // Content-Type: application/octet-stream
    // ══════════════════════════════════════════════════════════════════════
    @POST
    @Path("upload/stream")
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    public Response uploadStream(
            @QueryParam("name") String name,
            InputStream data,
            @Context UriInfo uriInfo) {
        // TODO: same as uploadBytes but use FileStorage.saveStream(name, data)
        // Note: InputStream is NOT buffered — reading is lazy.
        //       The whole body is NOT in memory yet when this method is called.
        throw new UnsupportedOperationException("TODO — Lab 5 Task 5.1");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 5 — Task 5.2: Download via StreamingOutput
    // GET /api/files/{filename}
    // GET /api/files/{filename}?inline=true   (display in browser)
    // ══════════════════════════════════════════════════════════════════════
    @GET
    @Path("{filename}")
    public Response download(
            @PathParam("filename") String filename,
            @QueryParam("inline") @DefaultValue("false") boolean inline)
            throws IOException {
        // TODO:
        //   1. Path file = safeResolve(filename)  ← MUST use safeResolve
        //   2. Return 404 if file does not exist
        //   3. Detect MIME: Files.probeContentType(file), fallback to APPLICATION_OCTET_STREAM
        //   4. Create StreamingOutput: out -> { Files.copy(file, out); out.flush(); }
        //   5. Build Content-Disposition: inline/attachment; filename="..."
        //   6. Return Response.ok(stream, mimeType)
        //          .header("Content-Length", Files.size(file))
        //          .header("Content-Disposition", cd)
        //          .build()
        throw new UnsupportedOperationException("TODO — Lab 5 Task 5.2");
    }

    // ══════════════════════════════════════════════════════════════════════
    // LAB 5 — Task 5.3: Path traversal prevention
    // ══════════════════════════════════════════════════════════════════════

    /**
     * Safely resolves a user-supplied filename against BASE_DIR.
     *
     * Security requirements:
     *   1. Reject filenames containing '/' or '\\' → throw BadRequestException
     *   2. Resolve: Path resolved = BASE_DIR.resolve(filename).normalize()
     *   3. Escape check: if !resolved.startsWith(BASE_DIR) → throw ForbiddenException
     *   4. Return the safe resolved path
     *
     * Test cases that must be blocked:
     *   "../../etc/passwd"      → contains /  → 400
     *   "%2F..%2Fpasswd"        → URL-decoded by Jersey → contains /  → 400
     *   "safe/../../../passwd"  → normalize() escapes BASE_DIR  → 403
     */
    private Path safeResolve(String filename) {
        // TODO: implement all three security checks above
        throw new UnsupportedOperationException("TODO — Lab 5 Task 5.3");
    }

    // ══════════════════════════════════════════════════════════════════════
    // Utility: list all stored files
    // GET /api/files
    // ══════════════════════════════════════════════════════════════════════
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public java.util.List<String> list() throws IOException {
        return FileStorage.getInstance().listFiles();
    }
}
