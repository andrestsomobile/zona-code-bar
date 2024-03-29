﻿select * from entrada where entcodsx  between 1720 and 1727

select * from config_bodega where cbentrada between 1720 and 1727


update  config_bodega set cbentrada = null  
G46-7
select * from config_bodega where cbestante= 'G' and cbposicion = '46-7'

select * from entrada where  entcodsx = 1


0) ejecutar script Koba-FUNCTION obtiene_posicion_libre V2 sin recursividad.sql
1) llenar mapa Koba-FUNCTION llenar_config_bodega.sql
2) marcar tuneles Koba-Marcar tuneles en Bodega.sql
3) select 'select movimiento.actualiza_posicion_en_bodega('||entcodsx||');' from entrada where char_length(entposicion) = 5 order by entrada
4) ejecuta el resultado del punto 2
5) update config_bodega  set cbentrada = null where cbentrada in (select entcodsx from entrada where entsaldonac + entsaldosinnac = 0)


listado de tuneles

select * from entrada where entposicion = 'G46-7'
"entcodsx";"entcodingreso";"entcodproducto";"entbodega";"entposicion";"entcantidad";"entpesoneto";"entpesonetototal";"entpesobruto";"entpesobrutototal";"entsaldopesoneto";"entsaldopesobruto";"entsaldonac";"entsaldosinnac";"entvalor";"entvalortotal";"resumen";"entfecha";"entsaldonacf";"entsaldosinnacf";"entunidad";"entlote"
33733;17;457;1;"G46-7";0.00;3.00;405.00;3.00;405.00;0.00;0.00;0.00;0.00;1.00;135.00;"12000928 GALLETA BISCOLATA MINI 117G G46-7 CEDI IMPORTADOS";"2019-10-01 11:20:40.093215";0.00;0.00;"U24";396

select * from config_bodega where cbestado = 'IN' order by cbestante,cbnivel,cbposicion

select * from config_bodega where cbestante= 'A' and cbposicion in ( '24-6','25-6')

select * from entrada where entsaldonac > 0 order by 1 asc limit 50

update entrada set entposicion = 'A24-6' where entcodsx = 16

update entrada set entposicion = 'G46-7' where entcodsx = 16

update entrada set entposicion = 'PISO' where entcodsx = 16

update entrada set entposicion = 'B11-1' where entcodsx = 16


update entrada set entposicion = 'A25-6' where entcodsx = 17

select movimiento.actualiza_posicion_en_bodega(16)

select movimiento.actualiza_posicion_en_bodega(17)

SELECT * FROM ENTRADA where entcodsx in (16,17)

select * from movimiento.valida_posicion_en_bodega(1, 'A25-6' ) 

SELECT substring('A25-6' from 1 for 1), substring('A25-6' from '....$'), substring('A25-6' from '.$') ::integer
FROM dual;


 select * from config_bodega where cbestante= 'I' and cbposicion in ('01-7')
          I01-7


        select cbestante||cbposicion as posicionbodega, promodelo, prodescripcion, proump, prounimasterpac, entcodsx, entposicion, entsaldonac, entsaldosinnac, 
        case when cbestado = 'IN' then 'TUNEL' else (case when cbentrada is not null then 'OCUPADA' else 'LIBRE' end) end as tunel,cbentrada,cbestante, cbnivel, cbposicion
        from config_bodega   
        left join entrada on entcodsx = cbentrada
        left join producto on procodsx = entcodproducto      
        union all
        select entposicion as posicionbodega, promodelo, prodescripcion, proump, prounimasterpac, entcodsx, entposicion, entsaldonac, entsaldosinnac,
        'OTRAS' as tunel,cbentrada,cbestante, cbnivel, cbposicion
        from entrada
        inner join producto on procodsx = entcodproducto
        left join config_bodega on cbentrada = entcodsx 
        where cbentrada is null and (entsaldonac+ entsaldosinnac) >0
        order by cbestante, cbnivel, cbposicion,entposicion


        select * from movimiento.es_tunel(1,'trytryrt')
        
        SELECT * FROM config_bodega 
        WHERE cbbodega = 1
        AND cbestante = 'C'
        AND cbnivel = '1'
        AND cbposicion = '33-1'
        AND cbestado = 'IN'; 