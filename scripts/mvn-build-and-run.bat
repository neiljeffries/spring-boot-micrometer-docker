@echo off
setlocal
pushd "%~dp0\.."

if exist mvnw.cmd (
  set MVN=.\mvnw.cmd
) else (
  set MVN=mvn
)

echo Building Spring Boot app (skip tests) ...
call "%MVN%" clean install -DskipTests || goto :end

echo Starting Spring Boot app ...
call "%MVN%" spring-boot:run

:end
set ERR=%ERRORLEVEL%
popd
echo.
pause
exit /b %ERR%
