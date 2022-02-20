<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Auction</title>
    <style><%@include file="/WEB-INF/css/normalize.css"%></style>
    <style><%@include file="/WEB-INF/css/style.css"%></style>
    <style><%@include file="/WEB-INF/css/main.css"%></style>
    <style><%@include file="/WEB-INF/css/util.css"%></style>

</head>
<body>
<!--page-wrapper-->
<div class="page-wrapper">

    <!--HEADER-->
    <%@include file="header.jsp"%>
    <!--CONTENT-->
    <div class="content-wrapper">
        <!--aside-->
        <aside class="aside"><a href="#">About us</a><a href="#">Delivery</a><a href="#">FAQ</a></aside>
        <!--first-section-->
        <div class="first-section">
            <h1>What Is an Auction?</h1>
            <p>An auction is a sales event wherein potential buyers place competitive bids on assets or services either in an open or closed format. Auctions are popular because buyers and sellers believe they will get a good deal buying or selling assets.</p>
            <h2>How Auctions Work</h2>
            <p>In an open format, all bidders are aware of the bids submitted. In a closed format, bidders are not aware of other bids. Auctions can be live, or they can be conducted on an online platform. The asset or service in question is sold to the party that places the highest bid in an open auction and usually to the highest bidder in a closed auction.</p>
            <h2>Open Auctions</h2>
            <p>In an open auction, parties come together at a physical venue or online exchange to bid on assets. An interested party is aware of the competing bid amounts and continues to raise their bid until they are either declared the winner of the auction (i.e., they submitted the last highest bid within the auction time limit) or until they decide to drop out of the bidding.</p>
            <h2>Closed Format Auctions</h2>
            <p>In many business transactions, including the sale of company assets or an entire company, auctions are conducted in a closed format whereby interested parties submit sealed bids to the seller. These bid amounts are only known by the seller. The seller may choose to hold just one round of bidding, or the seller may select two or more bidders for an additional auction round.</p>
            <h2>Government Auctions</h2>
            <p>Property may become government-owned property through normal purchases or if it is foreclosed on for failure to pay taxes, or for other reasons. Investors interested in land and other assets can attend an auction of government-owned property, which may ultimately be sold at attractive prices.</p>
            <h2>Key takeaways</h2>
            <ul>
                <li>An auction is a sale in which buyers compete for an asset by placing bids.</li>
                <li>Auctions are conducted both live and online.</li>
                <li>In a closed auction, for example, the sale of a company, bidders are not aware of competing bids.</li>
                <li>In an open auction, such as a livestock auction, bidders are aware of the other bids.</li>
                <li>Examples of auctions include livestock markets where farmers buy and sell animals, car auctions, or an auction room at Sotheby's or Christie's where collectors bid on works of art.</li>
            </ul>
        </div>
    </div>

    <!--FOOTER-->
    <%@include file="footer.jsp"%>
</div>
</body>
</html>
