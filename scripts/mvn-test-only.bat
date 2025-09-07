@echo off
setlocal
pushd "%~dp0\.."

if exist mvnw.cmd (
  set MVN=.\mvnw.cmd
) else (
  set MVN=mvn
)

echo Running tests only ...
call "%MVN%" -DskipTests=false -Dtest="*" test
set ERR=%ERRORLEVEL%

popd
echo.
pause
exit /b %ERR%
