-- 코드를 작성해주세요
SELECT
    b.ITEM_ID,b.ITEM_NAME,b.RARITY
FROM
    ITEM_TREE a
JOIN
    (SELECT ITEM_ID FROM  ITEM_INFO WHERE RARITY='RARE') as c
ON
  a.PARENT_ITEM_ID = c.ITEM_ID
JOIN
    ITEM_INFO b
ON
    a.ITEM_ID = b.ITEM_ID
ORDER BY
    ITEM_ID DESC
