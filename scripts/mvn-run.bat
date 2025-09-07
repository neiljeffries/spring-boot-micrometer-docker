@echo off
setlocal
pushd "%~dp0\.."

if exist mvnw.cmd (
  set MVN=.\mvnw.cmd
) else (
  set MVN=mvn
)

echo Running Spring Boot app with spring-boot:run in %CD% ...
call "%MVN%" spring-boot:run
set ERR=%ERRORLEVEL%

popd
echo.
pause
exit /b %ERR%
