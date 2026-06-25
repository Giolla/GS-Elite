# GS-Elite API

Spring Boot 3 (Java 21) backend for the Elite Dangerous fan site. Serves ship and
song metadata from Firestore and returns Firebase Storage URLs for images and audio.

## Endpoints

| Method | Path          | Description                  |
|--------|---------------|------------------------------|
| GET    | `/ships`      | All ships                    |
| GET    | `/ships/{id}` | Single ship by slug          |
| GET    | `/songs`      | All song groups (ordered)    |
| GET    | `/actuator/health` | Health check (Cloud Run probe) |

## Running locally

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=local
```

The `local` profile reads Firebase credentials from the path in
`application-local.yml` (gitignored). The `prod` profile reads them from the
`FIREBASE_SERVICE_ACCOUNT_JSON` environment variable.

## CI/CD

- **CI** (`.github/workflows/ci.yml`) — runs `mvn test` on every push and PR to `trunk`.
- **Deploy** (`.github/workflows/deploy.yml`) — builds the Docker image and deploys
  to Cloud Run, triggered by pushing a `v*` tag. Authenticates to Google Cloud via
  Workload Identity Federation (no stored credentials). Includes a post-deploy smoke
  test against `/ships` with automatic rollback to the previous revision on failure.

## Releasing

Releases are cut by pushing a version tag. The tag name (minus the `v`) becomes the
artifact version — `pom.xml` stays at `SNAPSHOT` in the repo and is stamped at build
time. The tag is the source of truth for the version.

```bash
git tag -a v1.0.0 -m "Initial release"
git push origin v1.0.0
```

Check the current released version with:

```bash
git tag --sort=-v:refname | head -1
```

or the **Releases** page on GitHub.

### Rollback

The deploy workflow auto-rolls back if the smoke test fails. To roll back manually,
re-push an earlier tag, or shift traffic directly:

```bash
gcloud run services update-traffic elite-api --region us-central1 --to-revisions REVISION-NAME=100
```
