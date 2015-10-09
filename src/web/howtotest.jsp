<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
    <h1 class="page-header">How to test</h1>
    <p>Aller først skal du ind i din tomcat-users.xml fil og tilføje dette : <code>&lt;role rolename=&quot;ca2-admin&quot; /&gt;
            &lt;user username=&quot;ca2-admini&quot; password=&quot;123123qwe&quot; roles=&quot;ca2-admin&quot; /&gt;</code>.
        Der efter skal du oprette din egen database, og ædre navnet på databasen i din persistence unit.
        Derefter skal du runne projektet, dit username er: <kbd>ca2-admin</kbd>, og dit password er: <kbd>123123qwe</kbd>.
    </p>
    <p>
        Når websiden kører, skal du trykke på person knappen for at få vist personerne i databasen,
        vi har oprettet nogle dummypersoner til at få vist.
        Hvis du vil have vist personer ud fra hobby, skal du skrive hobbyen i det tomme felt og trykke på 'search on hobby'-knappen.
        hvis du vil tilføje en person, skal du trykke på 'add new person'-knappen, udfylde felterne der popper op i tabellen,
        og trykke på 'save person'-knappen.
        Vil du fjerne en person skal du trykke på delete knappen ud fra personen.
        Vil du redigere en person, skal du trykke på edit knappen udfra den givne person.
        Husk hver gang du har ændret noget, skal du trykke på refresh knappen, for at få det vist.           
    </p>
</t:wrapper>
