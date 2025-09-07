@echo off
setlocal
pushd "%~dp0\.."

if exist mvnw.cmd (
  set MVN=.\mvnw.cmd
) else (
  set MVN=mvn
)

echo Building Spring Boot app (skip tests) in %CD% ...
call "%MVN%" clean install -DskipTests
set ERR=%ERRORLEVEL%

popd
echo.
pause
exit /b %ERR%
