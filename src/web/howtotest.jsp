<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<t:wrapper>
    <h1 class="page-header">How to test</h1>
    <table class="table">
        <tr>
            <td>Aller f�rst skal du ind i din tomcat-users.xml fil og tilf�je dette : &lt;role rolename=&quot;ca2-admin&quot; /&gt;
  &lt;user username=&quot;ca2-admini&quot; password=&quot;123123qwe&quot; roles=&quot;ca2-admin&quot; /&gt;.
            Der efter skal du oprette din egen database, og �dre navnet p� databasen i din persistence unit.
            Derefter skal du runne projektet, dit username er: ca2-admin, og dit password er: 123123qwe.
            N�r websiden k�rer, skal du trykke p� person knappen for at f� vist personerne i databasen,
            vi har oprettet nogle dummypersoner til at f� vist.
            Hvis du vil have vist personer ud fra hobby, skal du skrive hobbyen i det tomme felt og trykke p� 'search on hobby'-knappen.
            hvis du vil tilf�je en person, skal du trykke p� 'add new person'-knappen, udfylde felterne der popper op i tabellen,
            og trykke p� 'save person'-knappen.
            Vil du fjerne en person skal du trykke p� delete knappen ud fra personen.
            Vil du redigere en person, skal du trykke p� edit knappen udfra den givne person.
            Husk hver gang du har �ndret noget, skal du trykke p� refresh knappen, for at f� det vist.           
            </td>
        </tr>
    </table>
</t:wrapper>
