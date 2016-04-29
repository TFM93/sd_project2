#!/bin/bash
username=sd0203
password=diogocardoso
confFile=rg.config

# Read configuration file
while read line
do
    splitIndex=(${line//=/ })
    value=${line##*=}

    if [[ "$splitIndex" =~ "REPO_HOST_NAME" ]]; then
    	repositoryHostName=${value}
    elif [[ "$line" =~ "REPO_PORT_NUM" ]]; then
    	repositoryPortNum=${value}
    elif [[ "$line" =~ "REFSITE_HOST_NAME" ]]; then
    	refSiteHostName=${value}
    elif [[ "$line" =~ "REFSITE_PORT_NUM" ]]; then
    	refSitePortNum=${value}
    elif [[ "$line" =~ "BENCH_HOST_NAME" ]]; then
    	benchHostName=${value}
    elif [[ "$line" =~ "BENCH_PORT_NUM" ]]; then
    	benchPortNum=${value}
    elif [[ "$line" =~ "PLAYG_HOST_NAME" ]]; then
    	playgHostName=${value}
    elif [[ "$line" =~ "PLAYG_PORT_NUM" ]]; then
    	playgPortNum=${value}
    elif [[ "$line" =~ "REFEREE_HOST_NAME" ]]; then
    	refereeHostName=${value}
    elif [[ "$line" =~ "COACH_HOST_NAME" ]]; then
    	coachHostName=${value}
    elif [[ "$line" =~ "CONTESTANT_HOST_NAME" ]]; then
    	contestantHostName=${value}
    elif [[ "$line" =~ "CONFIG_HOST_NAME" ]]; then
    	configurationHostName=${value}
    elif [[ "$line" =~ "CONFIG_PORT_NUM" ]]; then
    	configurationPortNum=${value}
    fi
done < $confFile


# Start Configuration Server
printf "Sending configuration file to Configuration Server...\n"
sshpass -p $password scp $confFile $username@$configurationHostName:/home/$username &> /dev/null

printf "\nClosing active servers ...\n"
{
sshpass -p $password ssh $username@$configurationHostName killall -u sd0203
sshpass -p $password ssh $username@$repositoryHostName killall -u sd0203
sshpass -p $password ssh $username@$refSiteHostName killall -u sd0203
sshpass -p $password ssh $username@$benchHostName killall -u sd0203
sshpass -p $password ssh $username@$playgHostName killall -u sd0203
sshpass -p $password ssh $username@$refereeHostName killall -u sd0203
sshpass -p $password ssh $username@$coachHostName killall -u sd0203
sshpass -p $password ssh $username@$contestantHostName killall -u sd0203
} &> /dev/null

printf "\nStarting Configuration Server ($configurationHostName, $configurationPortNum) ...\n"
sshpass -p $password scp jars/configuration.jar $username@$configurationHostName:/home/sd0203/jars/
sleep 2

printf "\nStarting Repository Server ($repositoryHostName, $repositoryPortNum) ...\n"
sshpass -p $password scp jars/repository.jar $username@$repositoryHostName:/home/sd0203/jars/
sleep 2

printf "\nStarting RefereeSite Server ($refSiteHostName, $refSitePortNum) ...\n"
sshpass -p $password scp jars/refSite.jar $username@$refSiteHostName:/home/sd0203/jars/

sleep 2

printf "\nStarting Bench Server ($benchHostName, $benchPortNum) ...\n"
sshpass -p $password scp jars/bench.jar $username@$benchHostName:/home/sd0203/jars/
sleep 2

printf "\nStarting PlayGround Server ($playgHostName, $playgPortNum) ...\n"
sshpass -p $password scp jars/playg.jar $username@$playgHostName:/home/sd0203/jars/
sleep 2

printf "\nStarting Referee Client ($refereeHostName) ...\n"
sshpass -p $password scp jars/referee.jar $username@$refereeHostName:/home/sd0203/jars/
sleep 2

printf "\nStarting Coach Client ($coachHostName) ...\n"
sshpass -p $password scp jars/coach.jar $username@$coachHostName:/home/sd0203/jars/
sleep 2

printf "\nStarting Contestant Client ($contestantHostName) ...\n"
sshpass -p $password scp jars/contestant.jar $username@$contestantHostName:/home/sd0203/jars/
sleep 2
