#!/bin/bash
username=sd0203
password=diogocardoso
confFile=rg.config

# Read configuration file
while read line
do
    splitIndex=`expr index "$line" =`

    if [[ "$line" =~ "REPO_HOST_NAME" ]]; then
    	repositoryHostName=${line:$splitIndex}
    elif [[ "$line" =~ "REPO_PORT_NUM" ]]; then
    	repositoryPortNum=${line:$splitIndex}    
    elif [[ "$line" =~ "REFSITE_HOST_NAME" ]]; then
    	refSiteHostName=${line:$splitIndex}
    elif [[ "$line" =~ "REFSITE_PORT_NUM" ]]; then
    	refSitePortNum=${line:$splitIndex}
    elif [[ "$line" =~ "BENCH_HOST_NAME" ]]; then
    	benchHostName=${line:$splitIndex}
    elif [[ "$line" =~ "BENCH_PORT_NUM" ]]; then
    	benchPortNum=${line:$splitIndex}
    elif [[ "$line" =~ "PLAYG_HOST_NAME" ]]; then
    	playgHostName=${line:$splitIndex}
    elif [[ "$line" =~ "PLAYG_HOST_NAME" ]]; then
    	playgPortNum=${line:$splitIndex}
    elif [[ "$line" =~ "REFEREE_HOST_NAME" ]]; then
    	refereeHostName=${line:$splitIndex}
    elif [[ "$line" =~ "COACH_HOST_NAME" ]]; then
    	coachHostName=${line:$splitIndex}
    elif [[ "$line" =~ "CONTESTANT_HOST_NAME" ]]; then
    	contestantHostName=${line:$splitIndex}
    elif [[ "$line" =~ "CONFIG_HOST_NAME" ]]; then
    	configurationHostName=${line:$splitIndex}
    elif [[ "$line" =~ "CONFIG_PORT_NUM" ]]; then
    	configurationPortNum=${line:$splitIndex}
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
sshpass -p $password ssh -f $username@$configurationHostName java -jar jars/configuration.jar $configurationPortNum $confFile
sleep 2

printf "\nStarting Repository Server ($repositoryHostName, $repositoryPortNum) ...\n"
sshpass -p $password ssh -f $username@$repositoryHostName java -jar jars/repository.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting RefereeSite Server ($refSiteHostName, $refSitePortNum) ...\n"
sshpass -p $password ssh -f $username@$refSiteHostName java -jar jars/refSite.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting Bench Server ($benchHostName, $benchPortNum) ...\n"
sshpass -p $password ssh -f $username@$benchHostName java -jar jars/bench.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting PlayGround Server ($playgHostName, $playgPortNum) ...\n"
sshpass -p $password ssh -f $username@$playgHostName java -jar jars/playg.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting Referee Client ($refereeHostName) ...\n"
sshpass -p $password ssh -f $username@$refereeHostName java -jar jars/referee.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting Coach Client ($coachHostName) ...\n"
sshpass -p $password ssh -f $username@$coachHostName java -jar jars/coach.jar $configurationHostName $configurationPortNum
sleep 2

printf "\nStarting Contestant Client ($contestantHostName) ...\n"
sshpass -p $password ssh -f $username@$contestantHostName java -jar jars/contestant.jar $configurationHostName $configurationPortNum
sleep 2