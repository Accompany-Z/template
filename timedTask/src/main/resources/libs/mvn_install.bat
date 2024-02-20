@echo off
chcp 65001 > nul
setlocal enabledelayedexpansion

rem 获取脚本文件的绝对路径
set "SCRIPT_DIR=%~dp0"
set "LOG_FILE=%SCRIPT_DIR%mvn_install.log"

rem 获取当前时间
for /f "delims=" %%a in ('wmic OS Get localdatetime ^| find "."') do set datetime=%%a
set "YY=%datetime:~2,2%"
set "MM=%datetime:~4,2%"
set "DD=%datetime:~6,2%"
set "HH=%datetime:~8,2%"
set "Min=%datetime:~10,2%"
set "Sec=%datetime:~12,2%"

rem 设置文件路径
set "ASPOSE_FILE=!SCRIPT_DIR!aspose-words-18.6-jdk16-crack.jar"
set "OJDBC_FILE=!SCRIPT_DIR!ojdbc8-8.jar"

rem 检查文件是否存在
if exist "!ASPOSE_FILE!" (
    set "ASPOSE_ARG=-Dfile=!ASPOSE_FILE!"
    set "ASPOSE_FOUND=!ASPOSE_FILE! 已找到,准备执行安装命令..."
) else (
    set "ASPOSE_ARG="
    set "ASPOSE_FOUND=!ASPOSE_FILE! 未在当前脚本目录中找到，请检查脚本位置或确认当前是否在项目resources\libs目录下..."
)

if exist "!OJDBC_FILE!" (
    set "OJDBC_ARG=-Dfile=!OJDBC_FILE!"
    set "OJDBC_FOUND=!OJDBC_FILE! 已找到,准备执行安装命令..."
) else (
    set "OJDBC_ARG="
    set "OJDBC_FOUND=!OJDBC_FILE! 未在当前脚本目录中找到，请检查脚本位置或确认当前是否在项目resources\libs目录下..."
)

rem 获取当前时间戳
set "TIMESTAMP=!YY!-!MM!-!DD! !HH!:!Min!:!Sec!"

rem 输出文件路径和是否找到的信息
echo [!TIMESTAMP!] aspose-words-18.10-jdk16.jar: !ASPOSE_FILE! >> %LOG_FILE%
echo [!TIMESTAMP!] ojdbc8.jar: !OJDBC_FILE! >> %LOG_FILE%
echo [!TIMESTAMP!] !ASPOSE_FOUND! >> %LOG_FILE%
echo [!TIMESTAMP!] !OJDBC_FOUND! >> %LOG_FILE%

rem Maven 安装 ojdbc8（仅在文件存在时执行）
if exist "!OJDBC_FILE!" (
    echo [!TIMESTAMP!] mvn install:install-file -Dfile=!OJDBC_FILE! -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=8 -Dpackaging=jar -DgeneratePom=true -U >> %LOG_FILE% 2>&1
    call mvn install:install-file -Dfile=!OJDBC_FILE! -DgroupId=com.oracle -DartifactId=ojdbc8 -Dversion=8 -Dpackaging=jar -DgeneratePom=true -U >> %LOG_FILE% 2>&1
)

rem Maven 安装 aspose-words（仅在文件存在时执行）
if exist "!ASPOSE_FILE!" (
    echo [!TIMESTAMP!] mvn install:install-file -Dfile=!ASPOSE_FILE! -DgroupId=com.aspose -DartifactId=aspose-words -Dversion=18.6 -Dpackaging=jar -Dclassifier=jdk16 -U >> %LOG_FILE% 2>&1
    call mvn install:install-file -Dfile=!ASPOSE_FILE! -DgroupId=com.aspose -DartifactId=aspose-words -Dversion=18.6 -Dpackaging=jar -Dclassifier=jdk16 -U >> %LOG_FILE% 2>&1
)

rem 输出执行结果和日志文件地址
echo.
echo [!TIMESTAMP!] 执行结果已记录到日志文件：%LOG_FILE%
echo [!TIMESTAMP!] 请按任意键关闭 DOS 窗口...
pause

endlocal
