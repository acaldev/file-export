#!/bin/bash

BOLD='\033[1m'
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
LIGHTBLUE='\033[1;34m'
NC='\033[0m' # No Color


# build all containers
function buildContainers {
    cd docker
    docker-compose build
}

# start all containers
function startContainers {
echo -e "${LIGHTBLUE}=============================================${NC}"
echo -e "${YELLOW}
   ███████╗████████╗ █████╗  ██████╗██╗  ██╗
   ██╔════╝╚══██╔══╝██╔══██╗██╔════╝██║ ██╔╝
   ███████╗   ██║   ███████║██║     █████╔╝ 
   ╚════██║   ██║   ██╔══██║██║     ██╔═██╗ 
   ███████║   ██║   ██║  ██║╚██████╗██║  ██╗
   ╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝╚═╝  ╚═╝                              
 ${NC}"
    echo -e "${LIGHTBLUE}=============================================${NC}"
    echo -e "*********************************************"
    cd docker
    docker-compose up -d
    echo -e "*********************************************"
    echo -e "   ${YELLOW}https://stack.test${NC}"
    echo -e "*********************************************"
}

# start all containers
function startAndLogContainers {
    cd docker
    docker-compose up
}

# stop all containers
function stopContainers {
    cd docker
    docker-compose down
}

# exit if workspace container its not running
function exitIfWorkspaceIsNotRunning {
    if [ $(docker ps | grep stack_workspace | grep "Up" | wc -l) = "0" ]; then
        echo "${RED}[ERROR]{$NC} There is no workspace container running please run ./stack.sh start"
        exit 1
    fi
}

# open ssh connection
function openSSHConnection {
    exitIfWorkspaceIsNotRunning
    docker exec -it stack_workspace sh
}

# open ssh connection windows
function openSSHConnectionWindows {
    exitIfWorkspaceIsNotRunning
    winpty docker exec -it stack_workspace sh
}

# run a npm commmand in client
function runNpmComand {
    exitIfWorkspaceIsNotRunning
    docker exec stack_workspace bash -c "cd html/CLIENT && ${*}"
}

# run a yarn commmand in client
function runYarnComand {
    exitIfWorkspaceIsNotRunning
    docker exec stack_workspace bash -c "cd html/CLIENT && ${*}"
}

# run a ng commmand in client
function runNgComand {
    exitIfWorkspaceIsNotRunning
    docker exec stack_workspace bash -c "cd html/CLIENT && ${*}"
}

# help
function helpCommand {
    echo -e ""
    echo -e " ${LIGHTBLUE}-----------------------------------------------------------------${NC}"
    echo -e " ${BOLD}Main commands:${NC}"
    echo -e ""
    echo -e "   ${YELLOW}build${NC}"
    echo -e "       - Build the stack app"
    echo -e "   ${YELLOW}start${NC}"
    echo -e "       - Starts all services of stack app"
    echo -e "   ${YELLOW}stop${NC}"
    echo -e "       - Stops all services of stack app"
    echo -e ""
    echo -e " ${LIGHTBLUE}-----------------------------------------------------------------${NC}"
    echo -e " ${BOLD}Advanced commands:${NC}"
    echo -e ""
    echo -e "   ${YELLOW}ssh${NC}"
    echo -e "       - Open a ssh connection to workspace in Linux"
    echo -e "   ${YELLOW}sshw${NC}"
    echo -e "       - Open a ssh connection to workspace in Windows"
    echo -e "   ${YELLOW}npm${NC}"
    echo -e "       - Executes a npm command in stack CLIENT example: npm install, npm update"
    echo -e "   ${YELLOW}yarn${NC}"
    echo -e "       - Executes a npm command in stack CLIENT example: yarn install, yarn update"
    echo -e "   ${YELLOW}ng${NC}"
    echo -e "       - Executes a ng command in stack CLIENT example: ng serve"
    echo -e ""
}

# ARGS

if [ "$1" = "build" ]; then
    buildContainers
    exit
fi

if [ "$1" = "start" ]; then
    startContainers
    exit
fi

if [ "$1" = "startAndLog" ]; then
    startAndLogContainers
    exit
fi

if [ "$1" = "stop" ]; then
    stopContainers
    exit
fi

if [ "$1" = "ssh" ]; then
    openSSHConnection
    exit
fi

if [ "$1" = "sshw" ]; then
    openSSHConnectionWindows
    exit
fi

if [ "$1" = "npm" ]; then
    runNpmComand $*
    exit
fi

if [ "$1" = "yarn" ]; then
    runYarnComand $*
    exit
fi

if [ "$1" = "ng" ]; then
    runNgComand $*
    exit
fi

if [ "$1" = "--help" ]; then
    helpCommand
    exit
fi

echo -e ""
echo -e " ${RED}ERROR${NC} Invalid Command.\n"
echo -e " ${BOLD}Example command:${NC} ./stack.sh [OPTION] [PARAMETERS]\n"
echo -e " type ${YELLOW}--help${NC} for list available commands"
echo -e ""